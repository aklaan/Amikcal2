package com.rdupuis.gamingtools.shaders;

import java.util.ArrayList;
import java.util.HashMap;

import com.rdupuis.gamingtools.components.AbstractGameObject;
import com.rdupuis.gamingtools.components.CompositeShape;
import com.rdupuis.gamingtools.components.shapes.Shape;
import com.rdupuis.gamingtools.components.Scene;


import android.opengl.GLES20;
import android.util.Log;

/**
 * le shader provider va référencer les program Shader a utiliser
 *
 * @author NC10
 */
public class ProgramShaderManager {


    public Scene mScene;
    public ArrayList<ProgramShader> shaderList;
    public HashMap<String, Integer> catalogShader;

    public HashMap<Shape, ProgramShader> gameObjectShaderList;

    public ProgramShader getCurrentActiveShader() {
        return mCurrentActiveShader;
    }

    private ProgramShader mCurrentActiveShader;

    // déclaration des attributs du shader : default
    public final String DEFAULT_VSH_ATTRIB_VERTEX_COORD = "aPosition";
    public final String DEFAULT_VSH_ATTRIB_COLOR = "aColor";
    public final String DEFAULT_VSH_ATTRIB_TEXTURE_COORD = "aTexCoord";

    public final String DEFAULT_VSH_UNIFORM_MVP = "uMvp";
    public final String DEFAULT_FSH_UNIFORM_TEXTURE = "tex0";

    public ProgramShader getDefaultShader() {
        return mDefaultShader;
    }

    public void setDefaultSader(ProgramShader mDefaultShader) {
        this.mDefaultShader = mDefaultShader;
    }

    private ProgramShader mDefaultShader;


    /**
     * getter & setter
     */
    public HashMap<Shape, ProgramShader> getGameObjectShaderList() {
        return gameObjectShaderList;
    }

    public void setGameObjectShaderList(HashMap<Shape, ProgramShader> gameObjectShaderList) {
        this.gameObjectShaderList = gameObjectShaderList;
    }


    /***
     * Constructeur
     */
    public ProgramShaderManager() {

        this.mCurrentActiveShader = null;
        catalogShader = new HashMap<String, Integer>();
        shaderList = new ArrayList<ProgramShader>();
        this.gameObjectShaderList = new HashMap<Shape, ProgramShader>();
    }


    /***
     * Ajouter un shader dans le catalogue
     */
    public void add(ProgramShader shader) {
        shader.getClass().getName();
        int newindex = catalogShader.size() + 1;
        catalogShader.put(shader.mName, newindex);
        shaderList.add(shader);
    }

    /**
     * récupérer un shader via son nom
     *
     * @param shaderName
     * @return
     */
    public ProgramShader getShaderByName(String shaderName) {
        ProgramShader result = null;
        if (catalogShader.get(shaderName) == null) {
            Log.e(this.getClass().getName(), "Shader " + shaderName
                    + " unknow on Catalog");
        } else {
            result = shaderList.get(catalogShader.get(shaderName) - 1);
        }
        return result;
    }

    /**
     * @param shaderName
     */
    public void use(String shaderName) {
        ProgramShader sh = this.getShaderByName(shaderName);
        this.use(sh);

    }

    /**
     * Activer l'utilisation d'un shader
     *
     * @param shader
     */
    public void use(ProgramShader shader) {

        // use program
        if (this.mCurrentActiveShader != shader
                || this.mCurrentActiveShader == null) {

            if (this.mCurrentActiveShader != null) {
                this.mCurrentActiveShader.disableAttribs();
            }
            GLES20.glUseProgram(shader.mGLSLProgram_location);
            this.mCurrentActiveShader = shader;

        }


    }

    /**
     * effectuer le rendu de la scene
     */
    public void renderScene(Scene scene) {

        for (AbstractGameObject gameObject : scene.getGOManager().GOList()) {

            //Dessiner les objets visibles
            if (gameObject.getVisibility()) {

                //Appel au shader de l'objet s'il en requiert un en particulier.
                //sinon on utilise le shader par defaut.
                if (this.getGameObjectShaderList().get(gameObject) != null) {
                    this.use(this.getGameObjectShaderList().get(gameObject));
                } else this.use(this.getDefaultShader());

                //Si on doit dessiner un objet "Composé"
                if (CompositeShape.class.isInstance(gameObject)) {

                    CompositeShape cgo = (CompositeShape) gameObject;

                    for (Shape shape : cgo.getShapeList()) {

                        this.render(shape, scene.getProjectionView());
                    }

                } else {
                    // on demande su shader de rendre l'objet
                    Shape shape = (Shape) gameObject;
                    this.render(shape, scene.getProjectionView());
                }


            }
        }
    }


    private void render(Shape shape, float[] projectionView) {

        //Appel au shader de l'objet s'il en requiert un en particulier.
        //sinon on utilise le shader par defaut.
        if (this.getGameObjectShaderList().get(shape) != null) {
            this.use(this.getGameObjectShaderList().get(shape));
        } else this.use(this.getDefaultShader());

        this.getCurrentActiveShader().draw(shape, projectionView);

    }

}
