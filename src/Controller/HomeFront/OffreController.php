<?php

namespace App\Controller\HomeFront;

use App\Repository\OffreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class OffreController extends AbstractController
{
    /**
     * @Route("/offres", name="home_front_offre")
     */


    public function index(OffreRepository $repository): Response
    {
        $offres=$repository->findAll();
        return $this->render('home_front/offre/afficheroffrefront.html.twig', [
            "offres"=>$offres
        ]);
    }

}
