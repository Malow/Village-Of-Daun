package com.malow.villageofdaun.gfx;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.utils.Array;
import com.malow.villageofdaun.gui.GUIScale;

public class LibgdxRenderer
{
	public Camera camera;
	
	private SpriteBatch imageBatch;
	private List<Image> images;

	private ModelBatch meshBatch;
	private AssetManager meshAssets;
	private Array<ModelInstance> meshes;

	private Environment environment;
	
	private String lastFps = "";
	private int fpsCounter = 0;
	private float fpsTimer = 0.0f;
    private BitmapFont fpsText;
	
	public void create()
	{
		//Texture.setEnforcePotImages(false);
		
		// Init camera
		PerspectiveCamera perspCam = new PerspectiveCamera(50, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera = new Camera(perspCam);
		camera.cam.near = 0.1f;
		camera.cam.far = 200f;
		camera.cam.update();

		// Init environment
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -0.5f, -1.0f, 0.5f));

		meshes = new Array<ModelInstance>();
		meshAssets = new AssetManager();
		meshBatch = new ModelBatch();
		
		imageBatch = new SpriteBatch();
		images = new ArrayList<Image>();
		
        this.fpsText = new BitmapFont();
        this.fpsText.setColor(Color.YELLOW);
	}

	public void render(float diff)
	{        
		// Clear screen and set viewport
		GL20 gl = Gdx.gl;
		gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		// Render 3D meshes, enable depth and cull.
		gl.glEnable(GL20.GL_DEPTH_TEST);
		gl.glEnable(GL20.GL_CULL_FACE);

		camera.cam.update();

		meshBatch.begin(camera.cam);
		meshBatch.render(meshes, environment);
		meshBatch.end();

		// Render UI stuff, disable depth and cull.
		gl.glDisable(GL20.GL_CULL_FACE);
		gl.glDisable(GL20.GL_DEPTH_TEST);

		imageBatch.begin();
		for(Image image : images)
		{
			image.sprite.draw(imageBatch);
		}
		this.fpsText.draw(this.imageBatch, this.lastFps, 1160 * GUIScale.ScaleX, 700 * GUIScale.ScaleY);
		imageBatch.end();

		// Log our FPS.
		this.fpsCounter++;
		this.fpsTimer += diff;
		if(this.fpsTimer > 1.0f)
		{
			this.fpsTimer -= 1.0f;
			System.out.println("FPS: " + Gdx.graphics.getFramesPerSecond() + " (" + this.fpsCounter + ")");
			this.lastFps = "FPS: " + Gdx.graphics.getFramesPerSecond() + " (" + this.fpsCounter + ")";
			this.fpsCounter = 0;
		}
	}

	public void dispose()
	{
		this.fpsText.dispose();
		imageBatch.dispose();
		for(Image image : images)
		{
			image.dispose();
		}
		images.clear();
		meshBatch.dispose();
        meshes.clear();
        meshAssets.dispose();
	}

	public Mesh createMesh(String modelPath)
	{
		meshAssets.load(modelPath, Model.class);
		while(!meshAssets.update())
			;
		
        Model model = meshAssets.get(modelPath, Model.class);
        
        // TODO: Fix proper, this is haxxfix as fuck
        if(modelPath == "data/grass.g3db")
        {
        	Texture texture = new Texture(Gdx.files.internal("data/grass.jpg"), true);
        	texture.setFilter(TextureFilter.MipMapLinearNearest, TextureFilter.Nearest);
        	texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        	model.materials.get(0).set(new TextureAttribute(TextureAttribute.Diffuse, texture));
        }
        
        ModelInstance instance = new ModelInstance(model);
        meshes.add(instance);
        
        Mesh mesh = new Mesh(instance);
        return mesh;
	}
	
	public void deleteMesh(Mesh mesh)
	{
		boolean worked = meshes.removeValue(mesh.instance, false);
		if(!worked)
			throw new RuntimeException("ERROR. Tried deleteing an instance of a model that wasn't in the array");
	}
	
	public Image createImage(String imagePath)
	{
		Texture texture = new Texture(Gdx.files.internal(imagePath));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		Sprite sprite = new Sprite(texture);
		sprite.setSize(texture.getWidth() * GUIScale.ScaleX, texture.getHeight() * GUIScale.ScaleY);
		sprite.setPosition(0.0f, 0.0f);
		
		Image image = new Image(texture, sprite);
		images.add(image);
		return image;				
	}
	
	public void deleteImage(Image image)
	{
		boolean worked = images.remove(image);
		if(!worked)
			throw new RuntimeException("ERROR. Tried deleteing an image that wasn't in the array");
	}
}
