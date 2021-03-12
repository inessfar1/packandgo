<?php

namespace App\Controller;

use App\Entity\Hotel;

use App\Form\HotelType;

use App\Repository\ChambreRepository;
use App\Repository\HotelRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class HotelController extends AbstractController
{
    /**
     * @Route("/admin/hotel/show", name="hotel_index", methods={"GET"})
     */
    public function index(HotelRepository $hotelRepository): Response
    {
        return $this->render('hotel/index.html.twig', [
            'hotels' => $hotelRepository->findAll(),
        ]);
    }

    /**
     * @Route("admin/hotel/new", name="hotel_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $hotel = new Hotel();
        $form = $this->createForm(HotelType::class, $hotel);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($hotel);
            $entityManager->flush();

            return $this->redirectToRoute('hotel_index');
        }

        return $this->render('hotel/new.html.twig', [
            'hotel' => $hotel,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("admin/hotel/{id}", name="hotel_show", methods={"GET"})
     */
    public function show(Hotel $hotel): Response
    {
        return $this->render('hotel/show.html.twig', [
            'hotel' => $hotel,
        ]);
    }

    /**
     * @Route("admin/hotel/edit/{id}", name="hotel_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Hotel $hotel): Response
    {
        $form = $this->createForm(HotelType::class, $hotel);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('hotel_index');
        }

        return $this->render('hotel/edit.html.twig', [
            'hotel' => $hotel,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("admin/hotel/{id}", name="hotel_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Hotel $hotel): Response
    {
        if ($this->isCsrfTokenValid('delete'.$hotel->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($hotel);
            $entityManager->flush();
        }

        return $this->redirectToRoute('hotel_index');
    }
    /**
     * @Route("/hotel/show", name="hotel_front", methods={"GET"})
     */
    public function indexFront(HotelRepository $hotelRepository): Response
    {
        return $this->render('hotel/indexFront.html.twig', [
            'hotels' => $hotelRepository->findAll(),
        ]);
    }
    /**
     * @Route("/chambre/show/{id}", name="chambre_front", methods={"GET"})
     */
    public function indexFrontChambre(HotelRepository $chambreRepository,$id): Response
    {
        $hotel=$chambreRepository->find($id);
        return $this->render('chambre/indexFront.html.twig', [
            'hotel' => $hotel
        ]);
    }
}
