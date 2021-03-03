<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class DefaultController extends AbstractController
{
    /**
     * @Route("/default", name="default")
     */
    public function index(): Response
    {
        return $this->render('default/chauffeur.html.twig', [
            'controller_name' => 'DefaultController',
        ]);
    }
    /**
     * @Route("/backend", name="back")
     */
    public function backend(): Response
    {
        return $this->render('backend/dashboard.html.twig', [
            'controller_name' => 'DefaultController',
        ]);
    }
    /**
     * @Route("/frontend", name="front")
     */
    public function frontend(): Response
    {
        return $this->render('base.html.twig', [
            'controller_name' => 'DefaultController',
        ]);
    }
}
