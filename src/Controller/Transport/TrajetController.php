<?php

namespace App\Controller\Transport;

use App\Entity\Chauffeur;
use App\Entity\Moydetran;
use App\Entity\Trajet;
use App\Form\TrajetType;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use App\Repository\TrajetRepository;
use Symfony\Component\Routing\Annotation\Route;

class TrajetController extends AbstractController
{

    /**
     * @Route("/transport/add_Trajet", name="add_trajet")
     */
    public function addTrajet(Request $request): Response
    {
        $trajet = new Trajet();
        $form = $this->createForm(TrajetType::class,$trajet);
        $form->handleRequest($request);


        if($form->isSubmitted()&& $form->isValid())
        {
            $trajet->setIduser('1');


            $entityManager = $this->getDoctrine()->getManager();
            $chauffeur = $entityManager->getRepository(Chauffeur::class)->find($trajet->getIdChauffeur());
            $chauffeur->setDisponibilite(0);
            $transport = $entityManager->getRepository(Moydetran::class)->find($trajet->getIdMoyTrans());
            $transport->setDisponibilite(0);
            //persist insertion dans l'orm
            $entityManager->persist($trajet);
            //flush màj de la bd
            $entityManager->flush();

            return $this->redirectToRoute('my_trajet');

        }

        return $this->render("transport/trajet/addtrajet.html.twig", [
            "form_title" => "Ajouter un trajet",
            "form" => $form->createView(),
        ]);
    }
    /**
     * @Route("/transport/trajet", name="my_trajet")
     */
    public function listTrajet(Request $request, PaginatorInterface $paginator)
    {
        $trajet = $this->getDoctrine()->getRepository(Trajet::class)->findByUser();

        $pagination = $paginator->paginate(
            $trajet,
            $request->query->getInt('page', 1), /*page number*/
            2 /*limit per page*/
        );
        return $this->render('transport/trajet/trajet.html.twig', [
            "trajet" => $pagination,
        ]);
    }
    /**
     * @Route("/transport/delete_trajet/{idTrajet}", name="delete_trajet")
     */
    public function deleteTrajet(int $idTrajet): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $trajet = $entityManager->getRepository(Trajet::class)->find($idTrajet);
        $chauffeur = $entityManager->getRepository(Chauffeur::class)->find($trajet->getIdChauffeur());
        $chauffeur->setDisponibilite(1);
        $transport = $entityManager->getRepository(Moydetran::class)->find($trajet->getIdMoyTrans());
        $transport->setDisponibilite(1);
        $entityManager->remove($trajet);
        $entityManager->flush();
        return $this->redirectToRoute('my_trajet');
    }
    /**
     * @Route("/transport/edit_Trajet/{idTrajet}", name="edit_trajet")
     */
    public function editTrajet(Request $request, int $idTrajet): Response
    {

        $entityManager = $this->getDoctrine()->getManager();

        $trajet = $entityManager->getRepository(Trajet::class)->find($idTrajet);
        $form = $this->createForm(TrajetType::class, $trajet);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $entityManager->flush();
            return $this->redirectToRoute('my_trajet');
        }

        return $this->render("transport/trajet/edit_trajet.html.twig", [
            "form_title" => "Modifier un Trajet",
            "form" => $form->createView(),
        ]);
    }
    /**
     * @Route("/transport/list_trajet", name="list_trajet")
     */
    public function listbackTrajet(Request $request, PaginatorInterface $paginator)
    {
        $trajet = $this->getDoctrine()->getRepository(Trajet::class)->findAll();

        $pagination = $paginator->paginate(
            $trajet,
            $request->query->getInt('page', 1), /*page number*/
            2 /*limit per page*/
        );
        return $this->render('transport/trajet/backtrajet.html.twig', [
            "trajet" => $pagination,
        ]);
    }
    /**
     * @Route("/transport/deleteback_trajet/{idTrajet}", name="deleteback_trajet")
     */
    public function deletebackTrajet(int $idTrajet): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $trajet = $entityManager->getRepository(Trajet::class)->find($idTrajet);
        $entityManager->remove($trajet);
        $entityManager->flush();
        return $this->redirectToRoute('list_trajet');
    }
}
