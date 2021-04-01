<?php

namespace App\Controller;

use App\Entity\PlanRes;
use App\Repository\PlanRepository;
use App\Repository\PlanResRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Endroid\QrCode\Builder\Builder;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;
use Endroid\QrCode\Label\Alignment\LabelAlignmentCenter;
use Endroid\QrCode\Label\Font\NotoSans;
use Endroid\QrCode\RoundBlockSizeMode\RoundBlockSizeModeMargin;
use Endroid\QrCode\Writer\PngWriter;

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
            ->labelText('Scanner le ticket')
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
     * @Route("/plan/create-checkout-session", name="checkout")
     */
    public function checkout()
    {
        \Stripe\Stripe::setApiKey('sk_test_51ITzQpDWjPU5uegJwMhDjZee2zrXPyMCTRlbEbLFll7yX13SInTSH2PK96oU3k4sIAojhFLv496LSBbORleThNR500f6QNoqIi');
    }






}
