<?php

namespace App\Controller;

use App\Entity\Agence;
use App\Form\AgenceType;
use App\Repository\AgenceRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\HttpFoundation\JsonResponse;

class AgenceController extends AbstractController
{
    /**
     * @Route("/admin/agence/show", name="agence_index", methods={"GET"})
     * @param AgenceRepository $agenceRepository
     * @return Response
     */
    public function index(AgenceRepository $agenceRepository): Response
    {
        $agences=$agenceRepository->findAll();

        return $this->render('agence/index.html.twig', [
            'agences' => $agences,
        ]);
    }

    /**
     * @Route("/admin/agence/new", name="agence_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $agence = new Agence();
        $form = $this->createForm(AgenceType::class, $agence);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($agence);
            $entityManager->flush();

            return $this->redirectToRoute('agence_index');
        }

        return $this->render('agence/new.html.twig', [
            'agence' => $agence,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/admin/agence/show/{id}", name="agence_show", methods={"GET"})
     */
    public function show(Agence $agence): Response
    {
        return $this->render('agence/show.html.twig', [
            'agence' => $agence,
        ]);
    }

    /**
     * @Route("/admin/agence/edit/{id}", name="agence_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Agence $agence): Response
    {
        $form = $this->createForm(AgenceType::class, $agence);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('agence_index');
        }

        return $this->render('agence/edit.html.twig', [
            'agence' => $agence,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/admin/agence/delete/{id}", name="agence_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Agence $agence): Response
    {
        if ($this->isCsrfTokenValid('delete'.$agence->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($agence);
            $entityManager->flush();
        }

        return $this->redirectToRoute('agence_index');
    }


    /**
     * @Route("/admin/agence/newJSON", name="agence_newJSON")
     * @Method("POST")
     */

    public function newJSON(Request $request)
    {
        $agence = new Agence();
        $logo = $request->query->get("logo");
        $nom = $request->query->get("nom");
        $adresse = $request->query->get("adresse");
        $email = $request->query->get("email");
        $tel = $request->query->get("tel");
        $em = $this->getDoctrine()->getManager();


        $agence->setLogo($logo);
        $agence->setNom($nom);
        $agence->setEmail($email);
        $agence->setAdresse($adresse);
        $agence->setTel($tel);
        $em->persist($agence);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($agence);
        return new JsonResponse($formatted);

    }






    /**
     * @Route("/admin/agence/delJSON", name="agence_delJSON")
     * @Method("DELETE")
     */

    public function delJSON(Request $request) {
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $agence = $em->getRepository(Agence::class)->find($id);
        if($agence!=null ) {
            $em->remove($agence);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Agence a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }else{
        return new JsonResponse("id agence invalide.");}


    }


    /**
     * @Route("/admin/agence/updateJSON", name="agence_updateJSON")
     * @Method("PUT")
     */
    public function updateJSON(Request $request) {
        $em = $this->getDoctrine()->getManager();
        $agence = $this->getDoctrine()->getManager()
            ->getRepository(Agence::class)
            ->find($request->get("id"));

        $agence->setNom($request->get("nom"));
        $agence->setAdresse($request->get("adresse"));
        $agence->setEmail($request->get("email"));
        $agence->setTel($request->get("tel"));

        $em->persist($agence);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($agence);
        return new JsonResponse("Agence a ete modifiee avec success.");

    }





    /**
     * @Route("/admin/agence/allJSON", name="agence_allJSON")
     */
    public function allJSON(NormalizerInterface $normalizer)
    {

        $agence = $this->getDoctrine()->getManager()->getRepository(Agence::class)->findAll();

        $jsonContent =$normalizer->normalize($agence,'json',['groups'=>'agences']);
        return new Response(json_encode($jsonContent));

        return new JsonResponse($jsonContent);

    }



    /**
     * @Route("/admin/agence/detailJSON", name="agence_detailJSON")
     * @Method("GET")
     */


    public function detailJSON(Request $request)
    {
        $id = $request->get("id");

        $em = $this->getDoctrine()->getManager();
        $agence = $this->getDoctrine()->getManager()->getRepository(Agence::class)->find($id);
        $encoder = new JsonEncoder();
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getNom();
        });
        $serializer = new Serializer([$normalizer], [$encoder]);
        $formatted = $serializer->normalize($agence);
        return new JsonResponse($formatted);
    }


}
