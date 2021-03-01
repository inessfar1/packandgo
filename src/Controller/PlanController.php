<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\PlanRepository;


class PlanController extends AbstractController
{
    /**
     * @Route("/plan", name="plan")
     */
    public function index(): Response
    {
        return $this->render('plan/index.html.twig', [
            'controller_name' => 'PlanController',
        ]);
    }

    /**
     * @param PlanRepository $repository
     * @return mixed
     * @Route("Back/Plan",name="PlanAff")
     */
    public function affichePlan(PlanRepository $repository){
        $plan=$repository->findAll();
        return $this->render('plan/affichePlanBack.html.twig', [
            'plan' => $plan
        ]);
    }



}
