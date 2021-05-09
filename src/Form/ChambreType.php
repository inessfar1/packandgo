<?php

namespace App\Form;

use App\Entity\Chambre;
use App\Entity\Hotel;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ChambreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('type',ChoiceType::class,[ 'choices' => [
                'Single' => 'Single',
                'Double' => 'Double',
                'Tripple' => 'Tripple'
            ]])
            ->add('vue',ChoiceType::class,[ 'choices' => [
                'Vue de mer' => 'Vue de mer',
                'Vue de terrasse' => 'Vue de terrasse',
                'Vue de Piscine' => 'Vue de Piscine'
            ]])
            ->add('duree',TextType::class,[
                "label"=>'nb de chambre:'
            ])
            ->add('prix')
            ->add('imageFile',FileType::class,['label'=>'image chambre','required'=>false
            ])
            ->add('hotel',EntityType::class,['class'=> Hotel::class,'choice_label'=>'nom'])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Chambre::class,
        ]);
    }
}
