<?php

namespace App\Form;

use App\Entity\Hotel;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class HotelType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('adresse',EmailType::class)
            ->add('etoile')
            ->add('num')
            ->add('imageFile',FileType::class,['label'=>'image hotel','required'=>false
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Hotel::class,
        ]);
    }
}
