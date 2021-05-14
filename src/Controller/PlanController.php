<?php

namespace App\Controller;


use App\Entity\Agence;
use App\Entity\Plan;
use App\Form\PlanType;
use App\Repository\AgenceRepository;
use App\Repository\PlanRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\HttpFoundation\JsonResponse;

class PlanController extends AbstractController
{
    /**
     * @Route("/admin/plan/show", name="plan_index", methods={"GET"})
     */
    public function index(PlanRepository $planRepository ): Response
    {   $order=1;
        $plans=$planRepository->findAll();

        return $this->render('plan/index.html.twig', [
            'plans' => $plans,
            'order' => $order
        ]);
    }

    /**
     * @Route("/admin/plan/new", name="plan_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $plan = new Plan();
        $form = $this->createForm(PlanType::class, $plan);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($plan);
            $entityManager->flush();

            return $this->redirectToRoute('plan_index');
        }

        return $this->render('plan/new.html.twig', [
            'plan' => $plan,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/admin/plan/show/{id}", name="plan_show", methods={"GET"})
     */
    public function show(Plan $plan): Response
    {
        return $this->render('plan/show.html.twig', [
            'plan' => $plan,
        ]);
    }

    /**
     * @Route("/admin/plan/edit/{id}", name="plan_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Plan $plan): Response
    {
        $form = $this->createForm(PlanType::class, $plan);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('plan_index');
        }

        return $this->render('plan/edit.html.twig', [
            'plan' => $plan,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/admin/plan/delete/{id}", name="plan_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Plan $plan): Response
    {
        if ($this->isCsrfTokenValid('delete'.$plan->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($plan);
            $entityManager->flush();
        }

        return $this->redirectToRoute('plan_index');
    }


    /**
     * @Route("/plan/show", name="plan_front", methods={"GET"})
     */
    public function indexFront(PlanRepository $planRepository): Response
    {
        return $this->render('plan/indexFront.html.twig', [
            'plans' => $planRepository->findAll(),
        ]);
    }

    /**
     * @Route("/admin/plan/search", name="plansearch")
     */
    public function searchPlanajax(Request $request)
    {
        $repository = $this->getDoctrine()->getRepository(Plan::class);
        $requestString=$request->get('searchValue');

        $plan = $repository->findPlanBySujet($requestString);


        return $this->render('plan/planajax.html.twig', [
            'plans' => $plan,
        ]);

    }
    /**
     * @Route("/admin/plan/pdf ", name="plan_pdf")
     */
    public function pdf(PlanRepository $Repository)
    {
        $plan = $Repository->findall();
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('plan/pdf.html.twig', [
            'title' => "Liste des plans",
            'plans' => $plan
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("Plans.pdf", [
            "Attachment" => true
        ]);
        return $this->redirectToRoute('plan_index');
    }

    /**
     * @Route("admin/plan/cro", name="cro")
     */
    public function orderSujetASC(PlanRepository $repository){
        $order=2;
        $plans=$repository->triSujetASC();
        return $this->render('plan/index.html.twig', [
            'plans' => $plans,
            'order' => $order
        ]);
    }

    /**
     * @Route("admin/plan/dec", name="dec")
     */
   public function orderSujetDESC(PlanRepository $repository){
        $order=1;
        $plans=$repository->triSujetDESC();
        return $this->render('plan/index.html.twig', [
            'plans' => $plans,
            'order' => $order
        ]);
    }


    /**
     * @Route("/admin/plan/allJSON", name="plan_allJSON")
     */
    public function allJSON(NormalizerInterface $normalizer)
    {

        $plan = $this->getDoctrine()->getManager()->getRepository(Plan::class)->findAll();

        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getAgence();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($plan);
        return new JsonResponse($formatted);

        return new JsonResponse($jsonContent);

    }





    /**
     * @Route("/admin/plan/delJSON", name="plan_delJSON")
     * @Method("DELETE")
     */

    public function delJSON(Request $request) {
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $plan = $em->getRepository(Plan::class)->find($id);
        if($plan!=null ) {
            $em->remove($plan);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Plan a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }else{
            return new JsonResponse("id Plan invalide.");}


    }

    /**
     * @Route("/admin/plan/updateJSON", name="plan_updateJSON")
     * @Method("PUT")
     */
    public function updateJSON(Request $request,NormalizerInterface $normalizer) {
        $em = $this->getDoctrine()->getManager();
        $plan = $this->getDoctrine()->getManager()
            ->getRepository(Plan::class)
            ->find($request->get("id"));

        $plan->setSujet($request->get("sujet"));
        $plan->setDescription($request->get("desc"));
        $plan->setPrix($request->get("prix"));

        $em->persist($plan);
        $em->flush();

        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getAgence();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($plan);
        return new JsonResponse($formatted);
    }



    /**
     * @Route("/admin/plan/newJSON", name="plan_newJSON")
     * @Method("POST")
     */

    public function newJSON(Request $request, AgenceRepository $repository)
    {
        $ag= $repository->find($request->get('agence'));
        $plan= new Plan();
        $date = new \DateTime('now');
        $plan->setImage($request->get('image'));
        $plan->setSujet($request->get('sujet'));
        $plan->setDescription($request->get('desc'));
        $plan->setAgence($ag);
        $plan->setDate($date);
        $plan->setPrix($request->get('prix'));
        $em = $this->getDoctrine()->getManager();
        $em->persist($plan);
        $em->flush();
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getAgence();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($plan);
        return new JsonResponse($formatted);

    }

    /**
     * @Route("/admin/plan/recJSON", name="plan_recJSON")
     * @Method("PUT")
     */
    public function recJSON(Request $request,NormalizerInterface $normalizer) {
        $em = $this->getDoctrine()->getManager();
        $plan = $this->getDoctrine()->getManager()
            ->getRepository(Plan::class)
            ->find($request->get("id"));
        $rec = $plan->getRec();
        if ($rec<2)
        $plan->setRec($rec+1);
        else {$plan->setRec($rec+1); $plan->setNbr(0) ;}

        $em->persist($plan);
        $em->flush();

        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getAgence();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($plan);
        return new JsonResponse($formatted);
    }


}
