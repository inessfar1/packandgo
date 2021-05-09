<?php

namespace App\Controller\Admin;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class StatGestOffreController extends AbstractController
{
    /**
     * @Route("/admin/stat/gest/offre", name="admin_stat_gest_offre")
     */
    public function index(): Response
    {
        return $this->render('admin/stat_gest_offre/statGestionOffre.html.twig', [
            'controller_name' => 'StatGestOffreController',
        ]);
    }
}
