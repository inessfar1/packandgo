<?php

namespace App\Controller;

use App\Repository\AgenceRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class AgenceController extends AbstractController
{
    /**
     * @Route("/agence", name="agence")
     */
    public function index(): Response
    {
        return $this->render('agence/index.html.twig', [
            'controller_name' => 'AgenceController',
        ]);
    }


/**
 * @param AgenceRepository $repository
 * @return mixed
 * @Route("Back/Agence",name="AgenceAff")
 */
public function afficheAgence(AgenceRepository $repository){
    $agence=$repository->findAll();
    return $this->render('agence/afficheAgenceBack.html.twig', [
        'agence' => $agence
    ]);
}

    /**
     * @Route("Back/AgenceSupp/{id}",name="AgenceDel")
     */
    public function supprimerAgence(AgenceRepository $repository, $id){
        $agence=$repository->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($agence);
        $em->flush();

        return $this->redirectToRoute('AgenceAff');
    }

}