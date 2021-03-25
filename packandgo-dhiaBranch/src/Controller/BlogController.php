<?php

namespace App\Controller;

use App\Entity\Blog;
use App\Form\BlogType;
use App\Repository\BlogRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class BlogController extends AbstractController
{
    /**
     * @Route("/blog", name="blog")
     */
    public function index(): Response
    {
        return $this->render('blog/FrontBlog.html.twig', [
            'controller_name' => 'BlogController',
        ]);
    }
    /**
     * @Route ("/AfficherBlog",name="afficherB")
     */
    public function AfficherBlog(BlogRepository $repository)
    {
        $Blog = $repository->findAll();
        return $this->render('blog/afficherBlog.html.twig', [
            'blog' => $Blog
        ]);
    }
    /**
     * @Route("/AjouterBlog",name="ajouterB")
     */
    public function AjouterBlog(Request $request){
        $blog=new Blog();
        $form=$this->createForm(BlogType::class,$blog);

        $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid()){
            $file=$blog->getImage();
            $filename=md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $em=$this->getDoctrine()->getManager();
            $blog->setImage($filename);
            $em->persist($blog);
            $em->flush();
            return $this->redirectToRoute("afficherB");
        }
        return $this->render("blog/index.html.twig",["f"=>$form->createView()]);



    }

    /**
     * @Route ("/SupprimerBlog/{id}",name="supprimerB")
     */
    public function SupprimerBlog(BlogRepository $repository , $id)
    {
        $blog = $repository->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($blog);
        $em->flush();
        return $this->redirectToRoute("afficherB");
    }
    /**
     * @Route ("/ModifierBlog/{id}",name="modifierB")
     */
    public function ModifierBlog(BlogRepository $repository , Request $request , $id){
        $blog=$repository->find($id);
        $form=$this->createForm(BlogType::class,$blog);

        $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid()){
            $file=$blog->getImage();
            $filename=md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images_directory'),
                    $filename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }

            $blog->setImage($filename);
            $em=$this->getDoctrine()->getManager();
            $em->persist($blog);
            $em->flush();
            return $this->redirectToRoute("afficherB");
        }
        return $this->render("blog/modifierBlog.html.twig",["f"=>$form->createView()]);
    }
    /**
     * @Route ("/pdf",name="pdfBlog")
     */
    public function pdfBlog(BlogRepository $repository)
    {
        $blog = $repository->findAll();
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        $pdfOptions->setIsRemoteEnabled(true);


        // Instantiate Dompdf with our options

        $dompdf = new Dompdf($pdfOptions);
        $html = $this->renderView("blog/pdf.html.twig", [
            'blog' => $blog
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->output(['isRemoteEnabled' => true]);
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);

    }
    /**
     * Lists searched entities.
     *
     * @Route("/recherche", name="demande")

     */
    public function searchuserAction(Request $request){
        $em = $this->getDoctrine()->getManager();


        $searchParameter = $request->get('id');

        //call repository function

        $entities = $em->getRepository(Blog::class)->findBy(array('title'=>$searchParameter));
        $status = 'error';
        $html = '';
        $html1 = '';

        if($entities){

            $data = $this->render('blog/afficherBlog.html.twig', array(
                'blog' => $entities,
            ));

            $status = 'success';
            $html =$data->getContent();

        }

        if(strlen($searchParameter)==0){
            $entities = $em->getRepository(Blog::class)->findAll();
            $data = $this->render('blog/afficherBlog.html.twig', array(
                'blog' => $entities,
            ));
            $status = 'error1';
            $html = $data->getContent();




        }
        $jsonArray = array(
            'status' => $status,
            'data' => $html,
        );
        $encoders = [new XmlEncoder(), new JsonEncoder()];
        $normalizers = [new ObjectNormalizer()];

        $serializer = new Serializer($normalizers, $encoders);
        $jsonContent = $serializer->serialize($entities, 'json',['groups'=>'students']);


        $response = new Response(json_encode($jsonContent));
        $response->headers->set('Content-Type', 'application/json; charset=utf-8');

        return $response;
    }
    /**
     * @Route ("/AfficherBlogFront",name="afficherBF")
     */
    public function AfficherBlogFront(BlogRepository $repository)
    {
        $Blog = $repository->findAll();
        return $this->render('blog/frontBlog.html.twig', [
            'blog' => $Blog
        ]);
    }
}
