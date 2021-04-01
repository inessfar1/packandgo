<?php

namespace App\Form;

use App\Entity\Agence;
use App\Entity\Pays;
use App\Entity\Plan;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class PlanType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('imageFile',FileType::class,[
                'label'=>'Image de ce plan:       ',
                'required'=>false,
                'attr'=>[
                    'placeholder'=>'Saisir une image ici...',

                ]

            ])
            ->add('sujet',TextType::class,[
                'label'=>'Sujet de ce plan',
                'attr'=>[
                    'placeholder'=>'Entrer le sujet ici...',

                ]
            ])
            ->add('description',TextareaType::class,[
                'label'=>'Description de ce plan',
                'attr'=>[
                    'placeholder'=>'Entrer la description ici...',

                ]
            ])
            ->add('date')
            ->add('prix',TextType::class,[
                'label'=>'Prix de ce plan',
                'attr'=>[
                    'placeholder'=>'Entrer le prix ici...',

                ]
            ])
            ->add('pays',EntityType::class,[
                'class'=>Pays::class,
                'choice_label'=>'name'
            ])
            ->add('agence',EntityType::class,[
                'class'=>Agence::class,
                'choice_label'=>'nom'
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Plan::class,
        ]);
    }
}
