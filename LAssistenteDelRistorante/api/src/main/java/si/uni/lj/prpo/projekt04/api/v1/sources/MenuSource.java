package si.uni.lj.prpo.projekt04.api.v1.sources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.uni.lj.prpo.projekt04.DTOs.MenuItemDTO;
import si.uni.lj.prpo.projekt04.beans.MenuBean;
import si.uni.lj.prpo.projekt04.management.MenuBeanManagement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/menu")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Menu", description = "Operations related to managing the restaurant's menu, including adding, updating, and retrieving menu items")
public class MenuSource {

    @Inject
    private MenuBean menuBean;

    @Inject
    private MenuBeanManagement menuBeanManagement;


    @GET
    @Operation(summary = "Get all menu items", description = "Returns all menu items")
    @APIResponses({
            @APIResponse(description = "List of menu items retrieved successfully.", responseCode = "200", content = @Content(schema = @Schema(implementation = MenuItemDTO.class)))
    })
    public Response getAllEmployees(){
        return Response
                .ok(menuBean.getAllMenuItems())
                .header("X-Total-Count", menuBean.getMenuListSize())
                .build();
    }

    @GET
    @Path("{id}")
    @Operation(summary = "Get menu item by id", description = "Returns one specific menu item")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = MenuItemDTO.class))),
            @APIResponse(description = "Menu Item Not found", responseCode = "404")
    })
    public Response getMenuItem(@PathParam("id") Integer id){

        var menuItem = menuBean.getMenuItemWithId(id);
        if(menuItem != null){

            return Response.ok(menuItem)
                    .status(Response.Status.OK)
                    .build();

        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    @Operation(summary = "Add new menu item", description = "Creates a new menu item with the provided details such as name, description, price, category, and dietary attributes. The menu item will be added to the restaurant's menu and available for customers to order")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    public Response addNewMenuItem(
            @RequestBody(
                    description = "Creates Menu Item",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MenuItemDTO.class))
            )
            MenuItemDTO menuItemDTO){
        boolean status = menuBeanManagement.createNewMenuItem(menuItemDTO);
        if(status){
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Update menu item", description = "Updates the details of an existing menu item with the provided information, such as name, description, price, category, and dietary attributes. The item will be updated in the restaurant's menu with the new details")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    public Response updateMenuItem(@PathParam("id") Integer id,
                                      @RequestBody(description = "Update Menu Item", content = @Content(schema = @Schema(implementation = MenuItemDTO.class)))
                                      MenuItemDTO menuItemDTO){
        boolean success = menuBeanManagement.updateMenuItem(id, menuItemDTO);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE()
    @Path("{id}")
    @Operation(summary = "Delete menu item", description = "Deletes an existing menu item from the restaurant's menu using the provided item ID. This action removes the item permanently from the menu, making it unavailable for customers to order")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    public Response deleteMenuItem(@PathParam("id") Integer id){
        boolean success = menuBean.deleteMenuItem(id);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



}
