<?php

namespace App\Controller\Transport;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TrajetController extends AbstractController
{
    /**
     * @Route("/transport/trajet", name="transport_trajet")
     */
    public function index(): Response
    {
        return $this->render('transport/trajet/trajet.html.twig', [
            'controller_name' => 'TrajetController',
        ]);
    }
}
