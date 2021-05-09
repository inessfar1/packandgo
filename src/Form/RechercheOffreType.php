<?php

namespace App\Form;

use App\Entity\RechercheOffre;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RechercheOffreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('minAnnee',DateType::class,[
                "required"=>false,
                "label"=>"Date de :"
            ])
            ->add('maxAnnee',DateType::class,[
                "required"=>false,
                "label"=>"Date a : "
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => RechercheOffre::class,
        ]);
    }
}
