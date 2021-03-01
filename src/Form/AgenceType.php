<?php

namespace App\Form;

use App\Entity\Agence;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TelType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Mime\Email;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AgenceType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom',TextType::class,[
                'label'=>'Nom Agence',
                'attr'=>[
                    'placeholder'=>'Entrer le nom ici',
                    'class'=>'form-control'
                ]
            ])
            ->add('pays',TextType::class,[
                'label'=>'Pays',
                'attr'=>[
                    'placeholder'=>'Entrer le pays ici',
                    'class'=>'form-control'
                ]
            ])
            ->add('ville',TextType::class,[
                'label'=>'Ville',
                'attr'=>[
                    'placeholder'=>'Entrer la ville ici',
                    'class'=>'form-control'
                ]
            ])
            ->add('adresse',TextareaType::class,[
                'label'=>'Addresse',
                'attr'=>[
                    'placeholder'=>'Entrer adresse ici',
                    'class'=>'form-control'
                ]
            ])
            ->add('email',EmailType::class,[
                'label'=>'Email',
                'attr'=>[
                    'placeholder'=>'Entrer email ici',
                    'class'=>'form-control'
                ]
            ])
            ->add('tel',TelType::class,[
                'label'=>'Tel',
                'attr'=>[
                    'placeholder'=>'Entrer num tel ici',
                    'class'=>'form-control'
                ]
            ])
            ->add('Ajouter',SubmitType::class,[
                'attr'=>[
        'class'=>'btn btn-primary']]
    )
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Agence::class,
        ]);
    }
}
