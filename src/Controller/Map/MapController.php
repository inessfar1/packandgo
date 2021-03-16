<?php

namespace App\Controller\Map;

use App\Repository\OffreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MapController extends AbstractController
{
    /**
     * @Route("/map/{id}", name="map")
     */
    public function index(OffreRepository $offreRepository,$id): Response
    {
        $offres=$offreRepository->find($id);
        return $this->render('map/map.html.twig', [
            "offres"=>$offres
        ]);
    }
}
