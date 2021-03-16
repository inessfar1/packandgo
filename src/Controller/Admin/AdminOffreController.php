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
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

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

    /**
     * @Route("/admin/searchOffreajax ", name="ajaxsearch")
     */
    public function searchOffreajax(Request $request,NormalizerInterface $Normalizer,OffreRepository $repository)
        {
            $repository = $this->getDoctrine()->getRepository(Offre::class);
            $requestString=$request->get('searchValue');
            $offres = $repository->findOffrebyname($requestString);

            return $this->render('admin/admin_offre/Offreajax.html.twig', [
                "offres"=>$offres
            ]);

        }

    /**
     * @Route("/admin/offretriecroissant ", name="trie_croissant")
     */

    public function trie_croissant(OffreRepository $repository)
    {
       $repository = $this->getDoctrine()->getRepository(Offre::class);
       $offres = $repository->triecroissant();
        return $this->render('admin/admin_offre/afficheroffre.html.twig', [
            "offres"=>$offres
        ]);
    }

    /**
     * @Route("/admin/offretriedecroissant ", name="trie_decroissant")
     */

    public function trie_decroissant(OffreRepository $repository)
    {
        $repository = $this->getDoctrine()->getRepository(Offre::class);
        $offres = $repository->triedecroissant();
        return $this->render('admin/admin_offre/afficheroffre.html.twig', [
            "offres"=>$offres
        ]);
    }

    /**
     * @Route("/admin/calendrier", name="calendrier")
     */
    public function calendrier_offre(OffreRepository $repository)
    {
        $offres=$repository->findAll();

        $rdvs = [];

        foreach ($offres as $event)
        {
          $rdvs[]=[
              'title'=>$event->getName(),
              'description'=>$event->getDescription(),
              'price'=>$event->getPrice(),
              'start'=>$event->getDateDebut()->format("Y-m-d"),
              'end'=>$event->getDateFin()->format("Y-m-d"),
              'backgroundColor'=> 'aquamarine',
              'borderColor'=> 'green',
              'textColor' => 'black'
                  ];
        }

        $data = json_encode($rdvs);
        return $this->render('admin/calendrier_offre/calendrier.html.twig',compact('data'));
    }

}
