<?php

namespace App\Form;

use App\Entity\Moydetran;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class MoyentransType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('type',  ChoiceType::class, [
                'choices'  => [
                    'bus' => 'bus',
                    'voiture de location' => 'voiture de location',
                ],
            ])
            ->add('matricule')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Moydetran::class,
        ]);
    }
}
