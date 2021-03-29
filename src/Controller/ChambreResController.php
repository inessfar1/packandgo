<?php

namespace App\Controller;

use App\Entity\ChambreRes;
use App\Form\ChambreResType;
use App\Repository\ChambreRepository;
use App\Repository\ChambreResRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

/**
 * @Route("/chambre/res")
 */
class ChambreResController extends AbstractController
{
    /**
     * @Route("/", name="chambre_res_index", methods={"GET"})
     */
    public function index(ChambreResRepository $chambreResRepository): Response
    {
        return $this->render('chambre_res/index.html.twig', [
            'chambre_res' => $chambreResRepository->findAll(),
        ]);
    }

    /**
     * @Route("/new/{id}", name="chambre_res_new", methods={"GET","POST"})
     */
    public function new(Request $request,$id,ChambreRepository $chambre): Response
    {
        $chambreRe = new ChambreRes();
        $user=$this->getUser();
        $chambreRe->setUser($user);
        $p=$chambre->find($id);
        $nb=$p->getDuree();
        $p->setDuree($nb-1);
        $chambreRe->setChambre($p);
        $form = $this->createForm(ChambreResType::class, $chambreRe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($chambreRe);
            $entityManager->flush();
            $this->addFlash('success','Votre reservation a été effectuée');


            return $this->redirectToRoute('global');
        }

        return $this->render('chambre_res/new.html.twig', [
            'chambre_re' => $chambreRe,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/create-checkout-session", name="checkout", methods={"POST"})
     */
    public function checkout(): Response
    {
        \Stripe\Stripe::setApiKey('sk_test_51IYtOIEalWCU0w9M5DvJ4FNFAGvcs9x1BkNtZBogmCFcJl288U5BLXV1bbhsr3TCfx5WJ1drDuqz5i1jXQq1xGYn00HmumQCfd');

        $session = \Stripe\Checkout\Session::create([
            'payment_method_types' => ['card'],
            'line_items' => [[
                'price_data' => [
                    'currency' => 'eur',
                    'product_data' => [
                        'name' => 'room',
                    ],
                    'unit_amount' => 101010,
                ],
                'quantity' => 1,
            ]],
            'mode' => 'payment',
            # These placeholder URLs will be replaced in a following step.
            'success_url'=>$this->generateUrl('success',[], UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url'=>$this->generateUrl('error',[], UrlGeneratorInterface::ABSOLUTE_URL),
        ]);
        return new JsonResponse([ 'id' => $session->id ]) ;
    }
    /**
     * @Route("/error", name="error")
     */
    public function error(): Response
    {
        return $this->render('chambre_res/error.html.twig', [

        ]);
    }

    /**
     * @Route("/success", name="success")
     */
    public function success(): Response
    {
        return $this->render('chambre_res/success.html.twig', [

        ]);
    }




    /**
     * @Route("/res/{id}", name="chambre_res_show", methods={"GET"})
     */
    public function show(ChambreRes $chambreRe): Response
    {
        return $this->render('chambre_res/show.html.twig', [
            'chambre_re' => $chambreRe,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="chambre_res_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, ChambreRes $chambreRe): Response
    {
        $form = $this->createForm(ChambreResType::class, $chambreRe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('chambre_res_index');
        }

        return $this->render('chambre_res/edit.html.twig', [
            'chambre_re' => $chambreRe,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="chambre_res_delete", methods={"DELETE"})
     */
    public function delete(Request $request, ChambreRes $chambreRe): Response
    {

        if ($this->isCsrfTokenValid('delete'.$chambreRe->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($chambreRe);
            $entityManager->flush();
        }

        return $this->redirectToRoute('chambre_res_index');
    }
}
