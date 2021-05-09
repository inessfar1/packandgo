<?php

namespace App\Controller\Admin;

use App\Entity\Pays;
use App\Form\PaysType;
use App\Repository\PaysRepository;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AdminPaysController extends AbstractController
{
    /**
     * @Route("/admin/afficherpays", name="afficher_pays")
     */
    public function index(PaysRepository $repository): Response
    {
        $pays=$repository->findAll();
        return $this->render('admin/admin_pays/afficherPays.html.twig', [
            "pays"=>$pays
        ]);
    }

    /**
     * @Route("/admin/ajouterPays", name="AjoutPays")
     * @Route("/admin/modifier_pays/{id}", name="modifierPays")
     */
    public function modifer_pays(Pays $pays=null,Request $request,ManagerRegistry $objectManager)
    {
        if(!$pays)
        {
            $pays = new Pays();
        }
        $form=$this->createForm(PaysType::class,$pays);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid())
        {
            $em=$objectManager->getManager();
            $em->persist($pays);
            $em->flush();
            $this->addFlash('success',"L'action a ete effectué");
            return $this->redirectToRoute('afficher_pays');
        }
        return $this->render('admin/admin_pays/modifierpays.html.twig', [
            "pays"=>$pays,
            "form"=>$form->createView()
        ]);
    }

    /**
     * @Route("/admin/supp/{id}",name="supPays")
     */
    public function supprimerPays(Pays $pays,Request $request,ManagerRegistry $objectManager)
    {
        if($this->isCsrfTokenValid("SUP".$pays->getId(),$request->get("_token")))
        {
            $em=$objectManager->getManager();
            $em->remove($pays);
            $em->flush();
            return $this->redirectToRoute('afficher_pays');
        }

    }
}
