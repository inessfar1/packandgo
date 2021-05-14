<?php

namespace App\Controller;

use App\Entity\PlanRes;
use App\Repository\PlanRepository;
use App\Repository\PlanResRepository;
use App\Repository\UtilisateurRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

//use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;
use Endroid\QrCode\Builder\Builder;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;
use Endroid\QrCode\Label\Alignment\LabelAlignmentCenter;
use Endroid\QrCode\Label\Font\NotoSans;
use Endroid\QrCode\RoundBlockSizeMode\RoundBlockSizeModeMargin;
use Endroid\QrCode\Writer\PngWriter;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Slim\Http\Request;
use Slim\Http\Response;
use Stripe\Stripe;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class PlanResController extends AbstractController
{

    public function QR( $id,PlanRepository $plan){
        $p=$plan->find($id);
        $user=$this->getUser()->getUsername();
        $name=$p->getSujet();
        $result = Builder::create()
            ->writer(new PngWriter())
            ->writerOptions([])
            ->data('Client: '.$user.' Plan: '.$name)
            ->encoding(new Encoding('UTF-8'))
            ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
            ->size(300)
            ->margin(10)
            ->roundBlockSizeMode(new RoundBlockSizeModeMargin())
            ->labelText($user.' plan '.$name)
            ->labelFont(new NotoSans(20))
            ->labelAlignment(new LabelAlignmentCenter())
            ->build();
        header('Content-Type: '.$result->getMimeType());

        $result->saveToFile('QRcode/'.'client'.$user.'plan'.$name.'.png');
    }


    public function email($name,$sujet,$email, \Swift_Mailer $mailer)
    {

        $message = (new \Swift_Message('Votre reservation du plan '.$sujet))
            ->setFrom('sokalie1000@gmail.com')
            ->setTo($email);
        $img = $message->embed(\Swift_Image::fromPath('QRcode/client'.$name.'plan'.$sujet.'.png'));

        $message
            ->setBody(
                $this->renderView(
                // templates/emails/registration.html.twig
                    'emails/registration.html.twig',
                    ['name' => $name,
                     'sujet' =>$sujet,
                        'img'  =>$img
                    ]
                ),
                'text/html'
            )


        ;

        $mailer->send($message);

    }



    /**
     * @Route("/plan/{id}", name="plan_res" )
     */
    public function add( $id,PlanRepository $plan, \Swift_Mailer $mailer){
        $planRes=new PlanRes();
        $user=$this->getUser();
        $planRes->setUser($user);
        $p=$plan->find($id);
        $planRes->setPlan($p);
        $this->QR($id,$plan);
        $name=$this->getUser()->getUsername();
        $sujet=$p->getSujet();
        $email=$this->getUser()->getSalt();
        $this->email($name,$sujet,$email,$mailer);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($planRes);
        $entityManager->flush();
        $this->addFlash('success','Votre réservation a été envoyée sur votre email');

        return $this->redirectToRoute('global');
    }


    /**
     * @Route("/admin/plan/resJSON", name="plan_resJSON" )
     */
    public function addJSON(\Symfony\Component\HttpFoundation\Request $request, PlanRepository $plan,UtilisateurRepository $utilisateur){
        $planRes=new PlanRes();
        $user=$utilisateur->find($request->get('user'));
        $planRes->setUser($user);
        $p=$plan->find($request->get('id'));
        $planRes->setPlan($p);
        $p->getNbr($p->getNbr()-1);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($planRes);
        $entityManager->flush();
        $entityManager->persist($p);
        $entityManager->flush();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getSujet();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($planRes);
        return new JsonResponse($formatted);
    }



}
