<?php

namespace App\Controller;

use App\Entity\Agence;
use App\Form\AgenceType;
use App\Repository\AgenceRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;


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

    /**
     * @param Request $request
     * @return Response
     * @Route("Back/AgenceAjout" ,name="AgenceAdd")
     */
    public function ajouterAgence(Request $request){
        $agence=new Agence();
        $form=$this->createForm(AgenceType::class,$agence);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()){
            $em=$this->getDoctrine()->getManager();
            $em->persist($agence);
            $em->flush();
            return $this->redirectToRoute('AgenceAff');

        }
        return $this->render('agence/ajouterAgence.html.twig',['form'=>$form->createView()]);
    }

}