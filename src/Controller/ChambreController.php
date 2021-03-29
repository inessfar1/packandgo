<?php

namespace App\Controller;

use App\Entity\Chambre;
use App\Form\ChambreType;
use App\Repository\ChambreRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;

class ChambreController extends AbstractController
{
    /**
     * @Route("/admin/chambre/show", name="chambre_index", methods={"GET"})
     */
    public function index(ChambreRepository $chambreRepository): Response
    {
        return $this->render('chambre/index.html.twig', [
            'chambres' => $chambreRepository->findAll(),
        ]);
    }

    /**
     * @Route("admin/chambre/new", name="chambre_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $chambre = new Chambre();
        $form = $this->createForm(ChambreType::class, $chambre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($chambre);
            $entityManager->flush();

            return $this->redirectToRoute('chambre_index');
        }

        return $this->render('chambre/new.html.twig', [
            'chambre' => $chambre,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/admin/list", name="chambre_list")
     */
    public function list(ChambreRepository $chambreRepository): Response
    {

        $chambres = $chambreRepository->findall();
        // Configure Dompdf according to your needs
        $pdfoptions = new Options();
        $pdfoptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfoptions);


        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('chambre/list.html.twig', [
            'title' => "Liste des chambres",
            'chambres'=> $chambres
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("chambre.pdf", [
            "Attachment" => true
        ]);
        return $this->redirectToRoute('chambre_index');

    }

    /**
     * @Route("/admin/chambre/show/{id}", name="chambre_show", methods={"GET"})
     */
    public function show(Chambre $chambre): Response
    {
        return $this->render('chambre/show.html.twig', [
            'chambre' => $chambre,
        ]);
    }

    /**
     * @Route("/admin/chambre/show/edit/{id}", name="chambre_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Chambre $chambre): Response
    {
        $form = $this->createForm(ChambreType::class, $chambre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('chambre_index');
        }

        return $this->render('chambre/edit.html.twig', [
            'chambre' => $chambre,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/admin/chambre/show/{id}", name="chambre_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Chambre $chambre): Response
    {
        if ($this->isCsrfTokenValid('delete'.$chambre->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($chambre);
            $entityManager->flush();
        }

        return $this->redirectToRoute('chambre_index');
    }
    /**
     * @Route("/chambre/recherche_chambre", name="ajaxsearch")
     */
    public function searchAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $chambre = $em->getRepository(chambre::class)->findEntitiesByString($requestString);
        if(!$chambre)
        {
            $result['chambre']['error']="chambre introuvable :( ";

        }else{
            $result['chambre']=$this->getRealEntities($chambre);
        }
        return new Response(json_encode($result));

    }
    public function getRealEntities($chambre){
        foreach ($chambre as $chambre){
            $realEntities[$chambre->getId()] = [$chambre->getType(), $chambre->getDuree(), $chambre->getPrix(), $chambre->getImage()];
        }
        return $realEntities;
    }
    /**
     * @Route("/admin/chambre/show", name="calnedrier_chambre")
     */
    public function calendrier(): Response
    {


        return $this->render('calendrier/index.html.twig');
    }

}
