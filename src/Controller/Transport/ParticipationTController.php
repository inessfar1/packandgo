<?php

namespace App\Controller\Transport;

use App\Entity\ParticipationT;
use App\Entity\Trajet;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
class ParticipationTController extends AbstractController
{
    /**
     * @Route("/transport/participationt", name="transport_participation_t")
     */
    public function index(): Response
    {
        return $this->render('transport/participationT/index.html.twig', [
            'controller_name' => 'ParticipationTController',
        ]);
    }
    /**
     * @Route("/transport/participerT/{idTrajet}", name="participerT")
     */
    public function participert(Request $request,$idTrajet):Response
    {
        $participer = new ParticipationT();

        $trajet=$this->getDoctrine()->getRepository(Trajet::class)->find($idTrajet);
        $participer->setIdTrajet($trajet);
        $participer->setIdUser(1);

        $entityManager=$this->getDoctrine()->getManager();
        $entityManager->persist($participer);
        $entityManager->flush();
        $basic  = new \Vonage\Client\Credentials\Basic("586b7725", "UmBnl6yMzg6GRhzG");
        $client = new \Vonage\Client($basic);

        $response = $client->sms()->send(
            new \Vonage\SMS\Message\SMS("21620028684", "pack&go", 'votre participation a été effectué')
        );

        $message = $response->current();

        if ($message->getStatus() == 0) {
            echo "The message was sent successfully\n";
        } else {
            echo "The message failed with status: " . $message->getStatus() . "\n";
        }

        return $this->redirectToRoute('my_trajet_par');
    }
    /**
     * @Route("/transport/participT/", name="my_trajet_par")
     */
    public function listTrajet(Request $request, PaginatorInterface $paginator)
    {
        $idUser = 1;
        $participation = $this->getDoctrine()->getRepository(ParticipationT::class)->findByUser($idUser);

        $pagination = $paginator->paginate(
            $participation,
            $request->query->getInt('page', 1), /*page number*/
            2 /*limit per page*/
        );
        return $this->render('transport/participationT/myparticip.html.twig', [
            "participation" => $pagination,
        ]);
    }
    /**
     * @Route("/transport/annulerparticipation/{id}", name="annuler_particp")
     */
    public function annulerparticipation(int $id): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $participation = $entityManager->getRepository(ParticipationT::class)->find($id);
        $entityManager->remove($participation);
        $entityManager->flush();
        return $this->redirectToRoute('my_trajet_par');
    }
    /**
     * @Route("/transport/paymentparticipation/{id}", name="paymentparticipation")
     */
    public function paymentAction(Request $request,$id)
    {

        $participation=$em=$this->getDoctrine()->getManager()->getRepository(ParticipationT::class)->find($id);



        $amount=$participation->getIdTrajet()->getPrix();


        if ($request->isMethod("GET")) {

            \Stripe\Stripe::setApiKey("sk_test_51IZNbPENcO7CBJ8DxNi4y7q507UobrxpkvGSFx5q4xX4J0Vi49R7uYdlzFWa4TQIX5dFZRdGqwGb5wU8fSsT9294003wniAGVS");



            //dump($amount);
            // Token is created using Checkout or Elements!
            // Get the payment token ID submitted by the form:
            $charge = \Stripe\Charge::create([
                'amount' => $amount,
                'currency' => 'usd',
                'description' => 'Example charge',
                'source' => 'tok_visa',
            ]);


            return $this->render('transport/participationT/payment.html.twig', array('id'=>$id));
        }


    }
}
