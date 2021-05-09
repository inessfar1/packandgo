<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Repository\ReclamationRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Utilisateur ;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;


class ReclamationController extends AbstractController
{
    /**
     * @Route("/admin/reclamation", name="reclamation_index", methods={"GET"})
     */
    public function index(ReclamationRepository $reclamationRepository): Response
    {
        $order = 1 ;
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
            'order'=>$order
        ]);
    }

    /**
     * @Route("/reclamation/new", name="reclamation_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $user= $this->getUser() ;

        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamation->setStatut('Encours') ;
            $reclamation->setUtilisateurs($user) ;
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reclamation);
            $entityManager->flush();

            return $this->redirectToRoute('global');
        }

        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),

        ]);
    }

    /**
     * @Route("/admin/reclamation/{id}", name="reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="reclamation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reclamation $reclamation): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->add('utilisateurs',EntityType::class,[
            'class'=>Utilisateur::class,
            'choice_label'=>'email'
        ]);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamation->setStatut('Confirme') ;
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reclamation_index');
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/admin/{id}", name="reclamation_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Reclamation $reclamation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($reclamation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('reclamation_index');
    }

    /**
     * @Route("/admin/cherchereclamation", name="ajaxsearch")
     */
    public function searchAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $Reclamation = $em->getRepository(Reclamation::class)->findEntitiesByString($requestString);
        if(!$Reclamation)
        {
            $result['Reclamation']['error']="Reclamation introuvable  ";

        }else{
            $result['Reclamation']=$this->getRealEntities($Reclamation);
        }
        return new Response(json_encode($result));

    }
    public function getRealEntities($Reclamation){
        foreach ($Reclamation as $Reclamation){
            $realEntities[$Reclamation->getId()] = [$Reclamation->getObject(), $Reclamation->getDescription(),$Reclamation->getStatut()];
        }
        return $realEntities;
    }

    /**
     * @Route ("admin/triup", name="croissant")
     */
    public function orderSujetASC(ReclamationRepository $repository){
        $order=2;
        $plans=$repository->triSujetASC();
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $plans,
            'order' => $order
        ]);
    }

    /**
     * @Route("admin/tridown", name="decroissant")
     */
    public function orderSujetDESC(ReclamationRepository $repository){
        $order=1;
        $plans=$repository->triSujetDESC();
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $plans,
            'order' => $order
        ]);
    }


    /**
     * @Route("admin/stat", name="stat")
     */
    public function stat(){
        $em = $this->getDoctrine()->getManager();
        $conn = $em->getConnection();

        $sqlAdmin2 = 'SELECT object , COUNT(*) AS toBeUsed FROM reclamation  GROUP BY object';
        $sqlNbReclamation = 'SELECT COUNT(*) AS nbReclamation FROM reclamation';
        $sqlNbCategorie = 'SELECT object, COUNT(DISTINCT object) AS nbCategorie FROM reclamation GROUP BY object';
        $stmtAdmin2 = $conn->prepare($sqlAdmin2);
        $stmtAdmin3 = $conn->prepare($sqlNbReclamation);
        $stmtAdmin4 = $conn->prepare($sqlNbCategorie);

        $stmtAdmin2->execute();
        $stmtAdmin3->execute() ;
        $stmtAdmin4->execute() ;
        $arrayAdmin2 = $stmtAdmin2->fetchAll();
        $arrayAdmin3 = $stmtAdmin3->fetchAll() ;
        $arrayAdmin4 = $stmtAdmin4->fetchAll() ;

        $data2 = array(['Categorie','Nombre de Reclamation']);
        foreach ($arrayAdmin2 as $item){
            array_push($data2,[$item['object'],intval($item['toBeUsed'])]);
        }
        $nbReclamation = 0;
        foreach ($arrayAdmin3 as $nb){
            $nbReclamation +=  intval($nb['nbReclamation']);
        }
        $nbCategorie = 0;
        foreach ($arrayAdmin4 as $nb){
            $nbCategorie +=  intval($nb['nbCategorie']);
        }
        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable($data2);
        $pieChart->getOptions()->setTitle('Pourcentages des reclamations pour chaque Object');
        $pieChart->getOptions()->setWidth(600);
        $pieChart->getOptions()->setHeight(400);

        return $this->render('backend/statrec.html.twig', array(

            'arrayAdmin2' => $arrayAdmin2,
            'piechart'=>$pieChart ,
            'nbReclamation' => $nbReclamation,
            'nbCategorie'=>$nbCategorie ,
        ));

    }




}
