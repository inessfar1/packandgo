<?php

namespace App\Controller\Transport;

use App\Entity\Moydetran;
use App\Form\MoydetranType;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class MoyentransController extends AbstractController
{

    /**
     * @Route("/transport/add_moyentran", name="add_moyentran")
     */
    public function addmoyentran(Request $request): Response
    {
        $moyentran = new Moydetran();
        $form = $this->createForm(MoydetranType::class,$moyentran);
        $form->handleRequest($request);


        if($form->isSubmitted()&& $form->isValid())
        {
            $moyentran->setDisponibilite(1);
            $entityManager = $this->getDoctrine()->getManager();

            $entityManager->persist($moyentran);

            $entityManager->flush();

            return $this->redirectToRoute('list_moyentran');

        }

        return $this->render("transport/moyentrans/addmoytrans.html.twig", [
            "form_title" => "Ajouter un moyen",
            "form" => $form->createView(),
        ]);
    }
    /**
     * @Route("/transport/list_moyentran", name="list_moyentran")
     */
    public function listmoyentrans(Request $request, PaginatorInterface $paginator)
    {
        $moyentrans = $this->getDoctrine()->getRepository(Moydetran::class)->findAll();

        $pagination = $paginator->paginate(
            $moyentrans,
            $request->query->getInt('page', 1), /*page number*/
            2 /*limit per page*/
        );
        return $this->render('transport/moyentrans/moyentrans.html.twig', [
            "moyentrans" => $pagination,
        ]);
    }
    /**
     * @Route("/transport/delete_moyen/{idMoyTrans}", name="delete_moyen")
     */
    public function deletemoyentrans(int $idMoyTrans): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $moyentrans = $entityManager->getRepository(Moydetran::class)->find($idMoyTrans);
        $entityManager->remove($moyentrans);
        $entityManager->flush();
        return $this->redirectToRoute('list_moyentran');
    }
    /**
     * @Route("/transport/edit_moyentran/{idMoyTrans}", name="edit_moyentran")
     */
    public function editmoyentran(Request $request, int $idMoyTrans): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $moyentran = $entityManager->getRepository(Moydetran::class)->find($idMoyTrans);
        $form = $this->createForm(MoydetranType::class,$moyentran);
        $form->handleRequest($request);


        if($form->isSubmitted()&& $form->isValid())
        {

            $entityManager->flush();

            return $this->redirectToRoute('list_moyentran');

        }

        return $this->render("transport/moyentrans/edit_moytrans.html.twig", [
            "form_title" => "Modifier un moyen",
            "form" => $form->createView(),
        ]);
    }
    /**
     * @Route("/transport/moyen/{idMoyTrans}", name="detail_moyen")
     */
    public function detailmoytrans(int $idMoyTrans): Response
    {
        $moyentrans = $this->getDoctrine()->getRepository(Moydetran::class)->find($idMoyTrans);

        return $this->render("transport/moyentrans/detailmoytrans.html.twig", [
            "moy" => $moyentrans,
        ]);
    }

}
