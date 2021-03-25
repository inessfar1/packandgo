<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Comment;
use App\Entity\Likecategorie;
use App\Entity\Utilisateur;
use App\Form\CategorieType;
use App\Form\CommentType;
use App\Repository\CategorieRepository;
use App\Repository\LikecategorieRepository;
use App\Repository\UtilisateurRepository;
use Endroid\QrCode\Builder\Builder;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;
use Endroid\QrCode\Label\Alignment\LabelAlignmentCenter;
use Endroid\QrCode\Label\Font\NotoSans;
use Endroid\QrCode\RoundBlockSizeMode\RoundBlockSizeModeMargin;
use Endroid\QrCode\Writer\PngWriter;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\Exception\TransportExceptionInterface;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;

class CategorieController extends AbstractController
{
    /**
     * @Route("/categorie", name="categorie")
     */
    public function index(): Response
    {
        return $this->render('categorie/index.html.twig', [
            'controller_name' => 'CategorieController',
        ]);
    }
    /**
     * @Route ("/admin/categorie/AfficherCategorie",name="afficherC")
     */
    public function AfficherForum(CategorieRepository $repository)
    {
        $Forum = $repository->findAll();
        return $this->render('categorie/afficherCategorie.html.twig', [
            'forum' => $Forum
        ]);
    }
    /**
     * @Route("/admin/categorie/new",name="ajouterC")
     */
    public function AjouterC(Request $request,UtilisateurRepository $repository,MailerInterface $mailer,AuthenticationUtils $util)
    {
        $forum = new Categorie();
        $form = $this->createForm(CategorieType::class, $forum);


        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {

            $email = (new Email())
                ->from('dhiaeddine.mimouna@esprit.tn')
                ->to('dhiaeddine.mimouna@esprit.tn')
                //->cc('cc@example.com')
                //->bcc('bcc@example.com')
                //->replyTo('fabien@example.com')
                //->priority(Email::PRIORITY_HIGH)
                ->subject('Time for Symfony Mailer!')
                ->text('Sending emails is fun again!')
                ->html('<h1>Nouveau Categorie :  ' . $forum->getTitle() . '</h1>');

            try {
                $mailer->send($email);
            } catch (TransportExceptionInterface $e) {
            }
            $em = $this->getDoctrine()->getManager();
            $user = $this->getUser();

            $userConnecter=$repository->findOneBy(array('username'=>$user->getUsername()));
            $forum->setUtilisateur($userConnecter);

            $em->persist($forum);
            $em->flush();
            return $this->redirectToRoute("afficherC");
        }
        return $this->render("categorie/index.html.twig", ["f" => $form->createView()]);
    }
    /**
     * @Route("/categorieFront",name="ajouterCF")
     */
    public function AjouterCF(Request $request,UtilisateurRepository $repository,MailerInterface $mailer,AuthenticationUtils $util)
    {
        $forum = new Categorie();
        $form = $this->createForm(CategorieType::class, $forum);


        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {


            $em = $this->getDoctrine()->getManager();
            $user = $this->getUser();

            $userConnecter=$repository->findOneBy(array('username'=>$user->getUsername()));
            $forum->setUtilisateur($userConnecter);

            $em->persist($forum);
            $em->flush();
            return $this->redirectToRoute("FrontCategorie");
        }
        return $this->render("categorie/ajouterCategorieFront.html.twig", ["f" => $form->createView()]);
    }
    /**
     * @Route("/likeC/{id}",name="likeC")
     */
    public function likeCF(Request $request,UtilisateurRepository $repository,$id,CategorieRepository $repository1)
    {
        $forum = new Likecategorie();








            $em = $this->getDoctrine()->getManager();
            $user = $this->getUser();

            $userConnecter=$repository->findOneBy(array('username'=>$user->getUsername()));
            $forum->setUtilisateur($userConnecter);
            $forum->setCategorie($repository1->find($id));
            $forum->setDate(new \DateTime());

            $em->persist($forum);
            $em->flush();
        return $this->redirect('/frontCategorieDetail/'.$id);

    }
    /**
     * @Route ("/admin/categorie/delete/{id}",name="supprimerC")
     */
    public function SupprimerForum(CategorieRepository $repository , $id)
    {
        $forum = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($forum);
        $em->flush();
        return $this->redirectToRoute("afficherC");
    }
    /**
     * @Route ("/admin/categorie/edit/{id}",name="modifierC")
     */
    public function ModifierForum(CategorieRepository $repository , Request $request , $id){
        $forum=$repository->find($id);
        $form=$this->createForm(CategorieType::class,$forum);

        $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid()){




            $em=$this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("afficherC");
        }
        return $this->render("categorie/modifierCategorie.html.twig",["f"=>$form->createView()]);
    }
    /**
     * @Route("/frontCategorieDetail/{id}", name="frontCD")
     */
    public function ForumDetails(CategorieRepository $repository,$id,Request $request,UtilisateurRepository $repository1,LikecategorieRepository $repository2)
    {$blog = $repository->find($id);
        $comment=new Comment();
        $form=$this->createForm(CommentType::class,$comment);
        $user = $this->getUser();
        if($user==null)
        {
            $userConnecter=new Utilisateur();

        }
        else{
            $userConnecter=$repository1->findOneBy(array('username'=>$user->getUsername()));
        }
        $data="".PHP_EOL;
        foreach ($blog->getComments() as $a ) {
            $data = $data . $a->getText() .PHP_EOL;
        }

        $result = Builder::create()
            ->writer(new PngWriter())
            ->writerOptions([])
            ->data('comments: '.$data)
            ->encoding(new Encoding('UTF-8'))
            ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
            ->size(300)
            ->margin(10)
            ->roundBlockSizeMode(new RoundBlockSizeModeMargin())
            ->labelText('Scanner le ticket')
            ->labelFont(new NotoSans(20))
            ->labelAlignment(new LabelAlignmentCenter())
            ->build();
        header('Content-Type: '.$result->getMimeType());

        $result->saveToFile($this->getParameter('images_directory').'/'.$id.'.png');


        $form->handleRequest($request);
        $forum=$repository->find($id);
        if($form->isSubmitted()&&$form->isValid()){

            $comment->setDate(new \DateTime());
            $comment->setUtilisateur($userConnecter);
            $comment->setCategorie($blog);
            $em=$this->getDoctrine()->getManager();

            $em->persist($comment);
            $em->flush();
            return $this->redirect('/frontCategorieDetail/'.$id);
        }
           $iduser=$userConnecter->getId();

        return $this->render('categorie/FrontCategorieDetail.html.twig', [
            'categorie' => $blog,
            'comment'=>$form->createView(),
            'islike'=>$repository2->findOneBySomeField($id,$iduser)
        ]);
    }
    /**
     * @Route ("/dislike/{id}",name="DislikeC")
     */
    public function Supprimerlike(LikecategorieRepository $repository,UtilisateurRepository $repository1 , $id)
    { $user = $this->getUser();

        $userConnecter=$repository1->findOneBy(array('username'=>$user->getUsername()));
        $forum = $repository->findOneBySomeField($id,$userConnecter->getId());
        $em = $this->getDoctrine()->getManager();
        $em->remove($forum);
        $em->flush();
        return $this->redirect('/frontCategorieDetail/'.$id);
    }
}
