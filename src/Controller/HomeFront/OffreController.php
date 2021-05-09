<?php

namespace App\Controller\HomeFront;

use App\Entity\RechercheOffre;
use App\Form\RechercheOffreType;
use App\Repository\OffreRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class OffreController extends AbstractController
{
    /**
     * @Route("/offres", name="home_front_offre")
     */


    public function index(OffreRepository $repository,PaginatorInterface $paginator,Request $request): Response
    {
            $rechercheOffre= new RechercheOffre();
            $form=$this->createForm(RechercheOffreType::class,$rechercheOffre);
            $form->handleRequest($request);
            $offres = $paginator->paginate(
            $repository->findAllWithPagination($rechercheOffre),
            $request->query->getInt('page', 1), /*page number*/
            3 /*limit per page*/
        );
        return $this->render('home_front/offre/afficheroffrefront.html.twig', [
            "offres"=>$offres,
            "form"=>$form->createView()
        ]);
    }

    /**
     * @Route("/offres/create-checkout-session", name="checkout")
     */
    public function checkout(OffreRepository $repository)
    {
        $offres = $repository->findAll();
        \Stripe\Stripe::setApiKey('sk_test_51ITrmKDemurknTpxCDhbkbloGf2Vp9zDeOfOF80IVNhYUS5RnsYtvcYPYXr1dyygpj70e127PbPpr5HRLqqspqSO00H1gDbJGa');
        $session = \Stripe\Checkout\Session::create([
                  'payment_method_types' => ['card'],
                   'line_items' => [[
                                 'price_data' => [
                                 'currency' => 'usd',
                                 'product_data' => [
                                  'name' => 'T-shirt',
        ],
        'unit_amount'=> 150,
        ],
        'quantity' => 1,
    ]],
    'mode' => 'payment',
    # These placeholder URLs will be replaced in a following step.
    'success_url'=> $this->generateUrl('success',[],UrlGeneratorInterface::ABSOLUTE_URL),
    'cancel_url' => $this->generateUrl('echec',[],UrlGeneratorInterface::ABSOLUTE_URL),
   ]);
        return new JsonResponse(['id'=>$session->id]);
    }

    /**
     * @Route("/offres/success", name="success")
     */

    public function success(OffreRepository $repository,PaginatorInterface $paginator,Request $request): Response
    {

        return $this->render('home_front/offre/success.html.twig', [

        ]);
    }

    /**
     * @Route("/offres/echec", name="echec")
     */


    public function echec(): Response
    {
        return $this->render('home_front/offre/echec.html.twig', [

        ]);
    }

}
