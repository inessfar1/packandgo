<?php

namespace App\Controller\HomeFront;

use App\Repository\PaysRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PaysController extends AbstractController
{
    /**
     * @Route("/pays", name="home_front_pays")
     */

    public function index(PaysRepository $repository): Response
    {
        $pays=$repository->findAll();
        return $this->render('home_front/pays/afficherpaysfront.html.twig', [
            "pays"=>$pays
        ]);
    }

    /**
     * @Route("/pays/{id}", name="afficheroffreparpays")
     */

    public function afficheroffreparpays(PaysRepository $repository,$id): Response
    {
        $pays=$repository->find($id);
        return $this->render('home_front/offre/afficheroffreparpays.html.twig',[
            "pays"=>$pays
        ]);
    }
    /**
     * @Route("/plan/show/{id}", name="afficherplan")
     */

    public function afficherplanparpays(PaysRepository $repository,$id): Response
    {
        $pays=$repository->find($id);
        return $this->render('plan/indexFront.html.twig',[
            "pays"=>$pays
        ]);
    }


}
