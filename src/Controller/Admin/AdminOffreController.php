<?php

namespace App\Controller\Admin;

use App\Entity\Offre;
use App\Form\OffreType;
use App\Repository\OffreRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AdminOffreController extends AbstractController
{
    /**
     * @Route("/admin/afficheroffre", name="afficherOffre")
     */

    public function index(OffreRepository $repository): Response
    {
        $offres=$repository->findAll();
        return $this->render('admin/admin_offre/afficheroffre.html.twig', [
            "offres"=>$offres
        ]);
    }

    /**
     * @Route("/admin/ajouterOffre", name="AjoutOffre")
     * @Route("/admin/modifier_offre/{id}", name="modifOffre")
     */

    public function modifer_offre(Offre $offres=null,Request $request,ManagerRegistry $objectManager): Response
    {
        if(!$offres)
        {
            $offres = new Offre();
        }
        $form=$this->createForm(OffreType::class,$offres);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em=$objectManager->getManager();
            $em->persist($offres);
            $em->flush();
            $this->addFlash('success',"L'action a ete effectué");
            return $this->redirectToRoute('afficherOffre');
        }
        return $this->render('admin/admin_offre/modiferoffre.html.twig', [
            "offres"=>$offres,
            "form"=> $form->createView()
        ]);
    }

    /**
     * @Route("/admin/suppoffre/{id}",name="supOffre")
     */
    public function supprimerOffre(Offre $offres,Request $request,ManagerRegistry $objectManager)
    {
        if($this->isCsrfTokenValid("SUP".$offres->getId(),$request->get("_token")))
        {
            $em=$objectManager->getManager();
            $em->remove($offres);
            $em->flush();
            $this->addFlash('success',"L'action a ete effectué");
            return $this->redirectToRoute('afficher_pays');
        }
    }
}
