<?php

namespace App\Controller\Transport;

use App\Entity\Moydetran;
use App\Form\MoyentransType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MoyentransController extends AbstractController
{
    /**
     * @Route("/transport/moyentrans", name="transport_moyentrans")
     */
    public function index(): Response
    {
        return $this->render('transport/moyentrans/moyentrans.html.twig', [
            'controller_name' => 'MoyentransController',
        ]);
    }
    /**
     * @Route("/transport/add_moyentran", name="add_moyentran")
     */
    public function addmoyentran(Request $request): Response
    {
        $moyentran = new Moydetran();
        $form = $this->createForm(MoyentransType::class,$moyentran);
        $form->handleRequest($request);


        if($form->isSubmitted()&& $form->isValid())
        {
            $moyentran->setDisponibilite(1);
            $entityManager = $this->getDoctrine()->getManager();

            $entityManager->persist($moyentran);

            $entityManager->flush();

            return $this->redirectToRoute('list_moyentran');

        }

        return $this->render("transport/moyentrans/addmoyen.html.twig", [
            "form_title" => "Ajouter un moyen de transport",
            "form" => $form->createView(),
        ]);
    }
}
