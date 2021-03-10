<?php

namespace App\Controller\Transport;

use App\Entity\ParticipationT;
use App\Entity\Trajet;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

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

}
