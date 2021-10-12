package cubyz.api;

import cubyz.world.blocks.Block;
import cubyz.world.blocks.Ore;
import cubyz.world.entity.EntityType;
import cubyz.world.items.Item;
import cubyz.world.items.Recipe;
import cubyz.world.terrain.biomes.BiomeRegistry;
import cubyz.world.terrain.worldgenerators.SurfaceGenerator;

/**
 * Contains the world-specific registries.
 */

public class CurrentWorldRegistries {

	public final Registry<Block>       blockRegistry         = new Registry<Block>(CubyzRegistries.BLOCK_REGISTRY);
	public final NoIDRegistry<Ore>     oreRegistry           = new NoIDRegistry<Ore>(CubyzRegistries.ORE_REGISTRY);
	public final Registry<Item>        itemRegistry          = new Registry<Item>(CubyzRegistries.ITEM_REGISTRY);
	public final NoIDRegistry<Recipe>  recipeRegistry        = new NoIDRegistry<Recipe>(CubyzRegistries.RECIPE_REGISTRY);
	public final Registry<EntityType>  entityRegistry        = new Registry<EntityType>(CubyzRegistries.ENTITY_REGISTRY);
	public final BiomeRegistry         biomeRegistry         = new BiomeRegistry(CubyzRegistries.BIOME_REGISTRY);
	
	// world generation
	public final Registry<SurfaceGenerator> worldGeneratorRegistry = new Registry<SurfaceGenerator>(CubyzRegistries.STELLAR_TORUS_GENERATOR_REGISTRY);
}
