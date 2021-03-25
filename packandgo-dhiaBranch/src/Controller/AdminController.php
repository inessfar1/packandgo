<?php

namespace App\Controller;

use App\Entity\Utilisateur;
use App\Form\LoginType;
use App\Form\UtildashType;
use App\Form\UtilisateurType;

use App\Repository\CategorieRepository;
use App\Repository\UtilisateurRepository;
use Doctrine\Persistence\ObjectManager;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request ;
use Doctrine\Persistence\ManagerRegistry ;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;

class AdminController extends AbstractController
{
    /**
     * @Route("/", name="global")
     */
    public function index(): Response
    {

        return $this->render('admin/home.html.twig') ;

    }

    /**
     * @Route("/user/{id}", name="globaluser")
     */
    public function indexuser(UtilisateurRepository $repository,$id): Response
    {
        $utilisateur = $repository->find($id) ;
        return $this->render('admin/home.html.twig',[
            "utilisateur"=>$id
        ]) ;
    }

    /**
     * @Route("/utilisateur/creation", name="utilisateur_creation")
     * @Route("/utilisateur/{id}", name="utilisateur_modification",methods="GET|POST")
     */
    public function ajouETmodif(Utilisateur $utilisateur = null, Request $request , ManagerRegistry $objectManager , UserPasswordEncoderInterface $encoder): Response
    {
        if(!$utilisateur)
        {
            $utilisateur = new Utilisateur() ;
        }

        $form = $this->createForm(UtilisateurType::class,$utilisateur) ; //bech twarik les champ l moujoudin
        $form->handleRequest($request) ;  //recuperer les valeur
        if($form->isSubmitted() && $form->isValid()){ //verifier le formulaire
            $passwordCrypte = $encoder->encodePassword($utilisateur,$utilisateur->getPassword()) ;
            $utilisateur->setPassword($passwordCrypte) ;
            $utilisateur->setRoles("ROLE_USER") ;
            $modif =  $utilisateur->getId() !== null ;
            $em = $objectManager->getManager();
            $em->persist($utilisateur); //prepaer (tekhou les valeur)
            $em->flush(); //aaporter les modification f base
            $this->addFlash("success",($modif) ? "La modification a ete effectuee":"l'ajout a ete effectuee") ;
            return $this->redirectToRoute("global") ;
        }



        return $this->render('admin/formulaire.html.twig',[
            "utilisateur" => $utilisateur,
            "form"=>$form->createView(),
            "isModification" => $utilisateur->getId() !== null // si element mouch moujoud trajaa false
        ]);
    }


    /**
     * @Route("/login", name="login")
     */
    public function login(AuthenticationUtils $util)
    {

        return $this->render('admin/index.html.twig',[
            "lastUserName"=>$util->getLastUsername(),
            "error"=>$util->getLastAuthenticationError()
        ]);

    }
    /**
     * @Route("/logout", name="logout")
     */
    public function logout()
    {
    }

    /**
     * @Route("/admin/global", name="back")
     */
    public function aff(): Response
    {
        return $this->render('dashboard.html.twig');
    }
    /**
     * @Route("/admin/affichage", name="util")
     */
    public function affichage(UtilisateurRepository $repository): Response
    {
        $utilisateur= $repository->findAll() ;
        return $this->render('backend/utilisateur.html.twig', [
            'util' => $utilisateur,

        ]);
    }
    /**
     * @Route("/affichageCategorieFront", name="FrontCategorie")
     */
    public function affichageCategorieFront(CategorieRepository $repository): Response
    {
        $utilisateur= $repository->findAll() ;
        return $this->render('categorie/frontCategorie.html.twig', [
            'categorie' => $utilisateur,

        ]);
    }

    /**
     * @Route("/admin/util/creation", name="admin_creation")
     * @Route("/admin/util/{id}", name="admin_modification",methods="GET|POST")
     */
    public function ajoutetmodifdash(Utilisateur $utilisateur = null, Request $request , ManagerRegistry $objectManager , UserPasswordEncoderInterface $encoder): Response
    {
        if(!$utilisateur)
        {
            $utilisateur = new Utilisateur() ;
        }

        $form = $this->createForm(UtildashType::class,$utilisateur) ; //bech twarik les champ l moujoudin
        $form->handleRequest($request) ;  //recuperer les valeur
        if($form->isSubmitted() && $form->isValid()){ //verifier le formulaire
            $passwordCrypte = $encoder->encodePassword($utilisateur,$utilisateur->getPassword()) ;
            $utilisateur->setPassword($passwordCrypte) ;
            $modif =  $utilisateur->getId() !== null ;
            $em = $objectManager->getManager();
            $em->persist($utilisateur); //prepaer (tekhou les valeur)
            $em->flush(); //aaporter les modification f base
            $this->addFlash("success",($modif) ? "La modification a ete effectuee":"l'ajout a ete effectuee") ;
            return $this->redirectToRoute("util") ;
        }



        return $this->render('backend/modification.html.twig',[
            "utilisateur" => $utilisateur,
            "form"=>$form->createView(),
            "isModification" => $utilisateur->getId() !== null // si element mouch moujoud trajaa false
        ]);
    }

    /**
     * @Route("/admin/util/{id}", name="admin_suppression",methods="delete")
     */
    public function suppression(Utilisateur $utilisateur, Request $request , ManagerRegistry $objectManager ): Response
    {
        if($this->isCsrfTokenValid("SUP". $utilisateur->getId(),$request->get('_token'))){
            $em = $objectManager->getManager();
            $em->remove($utilisateur);
            $em->flush();
            $this->addFlash("success","La suppression a ete effectuee") ;
            return $this->redirectToRoute("util") ;
        }

    }

    /**
     * @Route("/profil", name="profil")
     */
    public function affichageprofil(): Response
    {

        return $this->render('admin/profil.html.twig');
    }

}
