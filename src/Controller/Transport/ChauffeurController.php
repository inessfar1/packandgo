<?php

namespace App\Controller\Transport;

use App\Entity\Chauffeur;
use App\Form\ChauffeurType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ChauffeurController extends AbstractController
{
    /**
     * @Route("/transport/chauffeur", name="transport_chauffeur")
     */
    public function index(): Response
    {
        return $this->render('transport/chauffeur/chauffeur.html.twig', [
            'controller_name' => 'ChauffeurController',
        ]);
    }

    public function addChauffeur(Request $request): Response
    {
        $Chauffeur = new Chauffeur();
        $form = $this->createForm(ChauffeurType::class,$Chauffeur);
        $form->handleRequest($request);

        if($form->isSubmitted()&& $form->isValid())
        {
            $entityManager = $this->getDoctrine()->getManager();

            $entityManager->persist($Chauffeur);

            $entityManager->flush();

            //return $this->redirectToRoute('showlivre');

        }

        return $this->render("transport/chauffeur/addchauffeur.html.twig", [
            "form_title" => "Ajouter un chauffeur",
            "form_chauffeur" => $form->createView(),
        ]);
    }
    public function editChauffeur(): Response
    {
        
    }

}
