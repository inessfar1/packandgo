<?php

namespace App\Controller;

use App\Entity\Utilisateur;
use App\Form\LoginType;
use App\Form\ModifProfilType;
use App\Form\ResetPassType;
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
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Csrf\TokenGenerator\TokenGeneratorInterface;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;

class AdminController extends AbstractController
{

    /**
     * @Route("/", name="global")
     */
    public function first(): Response
    {
        return $this->render('base.html.twig') ;
    }

    /**
     * @Route("/global", name="global1")
     */
    public function index(): Response
    {
        $user = $this->getUser() ;


        return $this->render('admin/home.html.twig',[
            'isActivation' => $user->getActivationToken() == null
            ]) ;


    }



    /**
     * @Route("/utilisateur/creation", name="utilisateur_creation")
     * @Route("/utilisateur/{id}", name="utilisateur_modification",methods="GET|POST")
     */
    public function ajouETmodif(Utilisateur $utilisateur = null, Request $request , ManagerRegistry $objectManager , UserPasswordEncoderInterface $encoder, \Swift_Mailer $mailer): Response
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

            // genere le token dactivation
            $utilisateur->setActivationToken(md5(uniqid())) ;


            $modif =  $utilisateur->getId() !== null ;
            $em = $objectManager->getManager();
            $em->persist($utilisateur); //prepaer (tekhou les valeur)
            $em->flush(); //aaporter les modification f base

            //mailing
            //on cree le message
            $message = (new \Swift_Message('Activation de votre compte'))
                //ili bech yeb3ath
                ->setFrom('hedi.driss@esprit.tn')
                //ili bech ijih l message
                ->setTo($utilisateur->getEmail())
                ->setBody(
                    $this->renderView(
                        'emails/activation.html.twig', ['token'=>$utilisateur->getActivationToken()]
                    ),
                    'text/html'
                )
            ;
            //on envoi l email
            $mailer->send($message) ;

            $this->addFlash("success",($modif) ? "La modification a ete effectuee":"vous avez ete inscri") ;
            return $this->redirectToRoute("global") ;


        }



        return $this->render('admin/formulaire.html.twig',[
            "utilisateur" => $utilisateur,
            "form"=>$form->createView(),
            "isModification" => $utilisateur->getId() !== null // si element mouch moujoud trajaa false
        ]);
    }

    /**
     * @Route("/activation2", name="activation2")
     */
    public function activation2( \Swift_Mailer $mailer){
        $user= $this->getUser() ;
        //mailing
        //on cree le message
        $message = (new \Swift_Message('Activation de votre compte'))
            //ili bech yeb3ath
            ->setFrom('hedi.driss@esprit.tn')
            //ili bech ijih l message
            ->setTo($user->getEmail())
            ->setBody(
                $this->renderView(
                    'emails/activation.html.twig', ['token'=>$user->getActivationToken()]
                ),
                'text/html'
            )
        ;
        //on envoi l email
        $mailer->send($message) ;

        $this->addFlash("success", "La modification a ete effectuee") ;
        return $this->redirectToRoute("global") ;

    }



    /**
     * @Route("/activation/{token}", name="activation")
     */
    public function activation(UtilisateurRepository $repository,$token){

        //verifier si lutilisateur a ce toekn
        $user = $repository->findOneBy(['activation_token' => $token]);
        //si aucun user nexiste avec ce token
        if(!$user){
            throw $this->createNotFoundException('cet utilisateur nexiste pas') ;
        }

        //on supprime le token
        $user->setActivationToken(null) ;
        $em = $this->getDoctrine()->getManager() ;
        $em->persist($user);
        $em->flush();
        $this->addFlash('success','vous avez bien activé votre compte') ;

        return $this->redirectToRoute('global') ;


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
     * @Route("/oubli-pass", name="app-forgotten-password")
     */
    public function forgottenPass(Request $request, UtilisateurRepository $repository, \Swift_Mailer $mailer, TokenGeneratorInterface $tokenGenerator)
    {
        $form = $this->createForm(ResetPassType::class) ;
        $form->handleRequest($request) ;
        if($form->isSubmitted() && $form->isValid()){
            //recupere les donnes
            $donnees = $form->getData() ;
            //on cherche si lutilisateur a cet email
            $user = $repository->findOneByEmail($donnees['email']) ;

            if(!$user){
                $this->addFlash('danger','cette adresse nexiste pas') ;
                $this->redirectToRoute('login') ;
            }

            //on genere un token
            $token = $tokenGenerator->generateToken() ;
            try{
                $user->setResetToken($token) ;
                $em = $this->getDoctrine()->getManager();
                $em->persist($user);
                $em->flush();
            }catch (\Exception $exception) {
                $this->addFlash('warning', 'une erreure est survenue : ' . $exception->getMessage());
                $this->redirectToRoute('login') ;
            }

            //on genere lurl du mot de passe
            $url= $this->generateURL('app_reset_password',['token'=>$token],
            UrlGeneratorInterface::ABSOLUTE_URL) ;

            //on envoie le message
            $message = (new \Swift_Message('Mot de passe oublie'))
                //ili bech yeb3ath
                ->setFrom('hedi.driss@esprit.tn')
                //ili bech ijih l message
                ->setTo($user->getEmail())
                ->setBody(
                    "<p>bonjour, </p><p> une demande de reinitialisation de mot de passe a ete effectue . veuillez cliquer sur le lien suivant : ". $url . '</p>',
                    'text/html'
                    )
            ;
            //on envoi l email
            $mailer->send($message) ;
            $this->addFlash('success','un email de reinitialisation de mot de passe vous a ete envoye') ;
            return $this->redirectToRoute('login') ;
        }
            return $this->render('admin/forgotten_password.html.twig', ['emailForm'=>$form->createView()]) ;
    }


    /**
     * @Route("/reset_pass/{token}", name="app_reset_password")
     */
    public function resetPassword($token, Request $request, UserPasswordEncoderInterface $encoder){
        //on chercher lutilisateur avec le token
        $user = $this->getDoctrine()->getRepository(Utilisateur::class)->findOneBy(['reset_token'=>$token]) ;
        if(!$user){
            $this->addFlash('danger','token inconnu') ;
            return $this->redirectToRoute('login') ;
        }
        //si le formulaire est envoyee en methode post
        if($request->isMethod('POST')) {
            //on suprime le token
            $user->setResetToken(null);
            //on chiffre le mot de passe
            $user->setPassword($encoder->encodePassword($user,$request->request->get('password')));
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            $this->addFlash('success','mot de passe modifiee avec succes') ;

            return $this->redirectToRoute('login') ;
        }else{
            return $this->render('admin/reset_password.html.twig',['token'=>$token]) ;
        }


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

            $this->addFlash("success",($modif) ? "La modification a ete effectuee":"ajout effectue") ;
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

    /**
     * @Route("/admin/desactive/{id}", name="admin_desactivation")
     */
    public function desactiver(Utilisateur $utilisateur, Request $request , ManagerRegistry $objectManager): Response
    {

            $utilisateur->setActivationToken((md5(uniqid())))  ;
            $em = $objectManager->getManager();
            $em->persist($utilisateur);
            $em->flush();
            $this->addFlash("success", "La desactivation a ete effectuee");
            return $this->redirectToRoute("util");

    }
    /**
     * @Route("/activation", name="admin_activation")
     */
    public function activationdash(Utilisateur $utilisateur, Request $request , ManagerRegistry $objectManager): Response
    {
        $user=$this->getUser() ;
        $user->setActivationToken(null) ;
        $em = $this->getDoctrine()->getManager() ;

        $em->flush();
        $this->addFlash('success','vous avez bien activé votre compte') ;

        return $this->redirectToRoute('global') ;

    }

    /**
     * @Route("/admin/utilisateur/search", name="utilsearch")
     */
    public function searchPlanajax(Request $request)
    {
        $repository = $this->getDoctrine()->getRepository(Utilisateur::class);
        $requestString=$request->get('searchValue');

        $plan = $repository->findPlanBySujet($requestString);


        return $this->render('backend/utilajax.html.twig', [
            'util' => $plan,
        ]);

    }

    /**
     * @Route("/profil/util/creation", name="profil_creation")
     * @Route("/profil/util/{id}", name="profil_modification",methods="GET|POST")
     */
    public function modifprofil(Utilisateur $utilisateur = null, Request $request , ManagerRegistry $objectManager , UserPasswordEncoderInterface $encoder): Response
    {
        if(!$utilisateur)
        {
            $utilisateur = new Utilisateur() ;
        }

        $form = $this->createForm(ModifProfilType::class,$utilisateur) ; //bech twarik les champ l moujoudin
        $form->handleRequest($request) ;  //recuperer les valeur
        if($form->isSubmitted() && $form->isValid()){ //verifier le formulaire
            $passwordCrypte = $encoder->encodePassword($utilisateur,$utilisateur->getPassword()) ;
            $utilisateur->setPassword($passwordCrypte) ;
            $modif =  $utilisateur->getId() !== null ;
            $em = $objectManager->getManager();
            $em->persist($utilisateur); //prepaer (tekhou les valeur)
            $em->flush(); //aaporter les modification f base

            $this->addFlash("success",($modif) ? "La modification a ete effectuee":"ajout effectue") ;
            return $this->redirectToRoute("profil") ;
        }



        return $this->render('admin/modificationprofil.html.twig',[
            "utilisateur" => $utilisateur,
            "form"=>$form->createView(),
            "isModification" => $utilisateur->getId() !== null // si element mouch moujoud trajaa false
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

}
