<?php

namespace App\Form;

use App\Entity\Agence;
use App\Entity\Pays;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AgenceType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('imageFile',FileType::class,[
                'label'=>'Logo de cette agence:       ',
                'required'=>false,
                'attr'=>[
                    'placeholder'=>'Saisir un logo ici...',

                ]

            ])
            ->add('nom',TextType::class,[
                'label'=>'Nom de cette agence',
                'attr'=>[
                    'placeholder'=>'Entrer le nom ici...',

                ]
            ])
            ->add('adresse',TextareaType::class,[
                'label'=>'Addresse de cette agence',
                'attr'=>[
                    'placeholder'=>'Entrer addresse ici...',

                    'class'=>'form-control'
                ]
            ])
            ->add('email',EmailType::class,[
                'label'=>'Email de cette agence',
                'attr'=>[
                    'placeholder'=>'Entrer email ici...',

                    'class'=>'form-control'
                ]
            ])
            ->add('tel',IntegerType::class,[
                'label'=>'Tel de cette agence',
                'attr'=>[
                    'placeholder'=>'Entrer le tel ici...',

                    'class'=>'form-control'
                ]
            ])

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Agence::class,
        ]);
    }
}
