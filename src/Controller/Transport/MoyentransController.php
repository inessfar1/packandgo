<?php

namespace App\Controller\Transport;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
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
}
