<?php

namespace App\Form;

use App\Entity\Pays;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class OffreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('description')
            ->add('name')
            ->add('price')
            ->add('imageFile',FileType::class,['required'=>false])
            ->add('dateDebut', DateType::class, [
                // renders it as a single text box
                'widget' => 'single_text',
            ])
            ->add('dateFin', DateType::class, [
                // renders it as a single text box
                'widget' => 'single_text',
            ])
            ->add('pays',EntityType::class,[
                'class'=> Pays::class,
                'choice_label'=> 'name'

            ]);
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            // Configure your form options here
        ]);
    }
}
