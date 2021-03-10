<?php

namespace App\Controller\Transport;

use App\Entity\Chauffeur;
use App\Form\ChauffeurType;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
class ChauffeurController extends AbstractController
{

    /**
     * @Route("/transport/add_chauffeur", name="add_chauffeur")
     */
    public function addChauffeur(Request $request): Response
    {
        $Chauffeur = new Chauffeur();
        $form = $this->createForm(ChauffeurType::class,$Chauffeur);
        $form->handleRequest($request);


        if($form->isSubmitted()&& $form->isValid())
        {
            $Chauffeur->setDisponibilite(1);
            $entityManager = $this->getDoctrine()->getManager();

            $entityManager->persist($Chauffeur);

            $entityManager->flush();

            return $this->redirectToRoute('list_chauffeur');

        }

        return $this->render("transport/chauffeur/addchauffeur.html.twig", [
            "form_title" => "Ajouter un chauffeur",
            "form" => $form->createView(),
        ]);
    }
    /**
     * @Route("/transport/edit_chauffeur/{idChauffeur}", name="edit_chauffeur")
     */
    public function editChauffeur(Request $request, int $idChauffeur): Response
    {

        $entityManager = $this->getDoctrine()->getManager();

        $chauffeur = $entityManager->getRepository(Chauffeur::class)->find($idChauffeur);
        $form = $this->createForm(ChauffeurType::class, $chauffeur);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid())
        {
            $entityManager->flush();
            return $this->redirectToRoute('list_chauffeur');
        }

        return $this->render("transport/chauffeur/edit_chauffeur.html.twig", [
            "form_title" => "Modifier un chauffeur",
            "form" => $form->createView(),
        ]);
    }
    /**
     * @Route("/transport/delete_chauffeur/{idChauffeur}", name="delete_chauffeur")
     */
    public function deleteChauffeur(int $idChauffeur): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $chauffeur = $entityManager->getRepository(Chauffeur::class)->find($idChauffeur);
        $entityManager->remove($chauffeur);
        $entityManager->flush();
        return $this->redirectToRoute('list_chauffeur');
    }
    /**
     * @Route("/transport/listchauffeur", name="list_chauffeur")
     */
    public function listChauffeur(Request $request, PaginatorInterface $paginator)
    {
        $chauffeur = $this->getDoctrine()->getRepository(Chauffeur::class)->findAll();

        $pagination = $paginator->paginate(
            $chauffeur,
            $request->query->getInt('page', 1), /*page number*/
            2 /*limit per page*/
        );
        return $this->render('transport/chauffeur/chauffeur.html.twig', [
            "chauffeur" => $pagination,
        ]);
    }
    /**
     * @Route("/transport/chauffeur/{idChauffeur}", name="detail_chauffeur")
     */
    public function detailchauffeur(int $idChauffeur): Response
    {
        $chauffeur = $this->getDoctrine()->getRepository(Chauffeur::class)->find($idChauffeur);

        return $this->render("transport/chauffeur/detailchauffeur.html.twig", [
            "ch" => $chauffeur,
        ]);
    }
    /**
     * @Route("/transport/recherche_trajet", name="ajaxsearch")
     */
    public function searchAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $Chauffeur = $em->getRepository(Chauffeur::class)->findEntitiesByString($requestString);
        if(!$Chauffeur)
        {
            $result['Chauffeur']['error']="Chauffeur introuvable :( ";

        }else{
            $result['Chauffeur']=$this->getRealEntities($Chauffeur);
        }
        return new Response(json_encode($result));

    }
    public function getRealEntities($Chauffeur){
        foreach ($Chauffeur as $Chauffeur){
            $realEntities[$Chauffeur->getIdChauffeur()] = [$Chauffeur->getNom(), $Chauffeur->getPrenom()];
        }
        return $realEntities;
    }
    }
