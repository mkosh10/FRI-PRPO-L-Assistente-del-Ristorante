package si.uni.lj.prpo.projekt04.api.v1.sources;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.uni.lj.prpo.projekt04.DTOs.EmployeeDTO;
import si.uni.lj.prpo.projekt04.DTOs.ReservationDTO;
import si.uni.lj.prpo.projekt04.beans.EmployeeBean;
import si.uni.lj.prpo.projekt04.management.EmployeeBeanManagement;

import javax.inject.Inject;
import javax.json.bind.JsonbException;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Employees", description = "Operations related to managing restaurant employees, including adding, updating, and retrieving employee details")
public class EmployeeSource {
    @Inject
    private EmployeeBean employeeBean;

    @Inject
    private EmployeeBeanManagement employeeBeanManagement;

    @GET
    @Operation(summary = "Retrieve all employees", description = "Fetches a list of all employees currently stored in the system, including their basic details")
    @APIResponses({
            @APIResponse(description = "List of employees retrieved successfully.", responseCode = "200", content = @Content(schema = @Schema(implementation = EmployeeDTO.class)))
    })
    public Response getAllEmployees(){
        return Response
                .ok(employeeBean.getAllEmployees())
                .header("X-Total-Count", employeeBean.getEmployeesListSize())
                .build();
    }


    @GET
    @Path("{id}")
    @Operation(summary = "Retrieve an employee by ID", description = "Returns employee information")
    @APIResponses({
            @APIResponse(description = "Employee retrieved successfully.", responseCode = "200", content = @Content(schema = @Schema(implementation = EmployeeDTO.class))),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    public Response getEmployee(@PathParam("id") Integer id){
        var employee = employeeBean.getEmployeeWithId(id);
        if(employee != null){
            return Response
                    .ok(employee)
                    .status(Response.Status.FOUND)
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Operation(summary = "Create a new employee", description = "Adds a new employee to the system. Requires details such as first name, last name, job position and salary.")
    @APIResponses({
            @APIResponse(description = "Created", responseCode = "201"),
            @APIResponse(description = "Bad Request", responseCode = "400")
    })
    public Response addNewEmployee(
            @RequestBody(
                    description = "Employee details",
                    required = true,
                    content = @Content(schema = @Schema(implementation = EmployeeDTO.class))
            )
            EmployeeDTO employeeDTO){
        try {

            boolean success = employeeBeanManagement.addNewEmployee(employeeDTO);
            if(success){
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch(JsonbException e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }


    @PUT
    @Path("{id}")
    @Operation(summary = "Update an employee's information", description = "Modifies the details of an existing employee based on their unique ID")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Bad Request", responseCode = "400"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    public Response updateEmployeeInfo(@PathParam("id") Integer id,
                                       @RequestBody(
                                               description = "Updated employee information",
                                               content = @Content(schema = @Schema(implementation = EmployeeDTO.class))
                                       )
                                       EmployeeDTO employeeDTO){
        boolean success = employeeBeanManagement.updateEmployeeInfo(id, employeeDTO);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Operation(summary = "Delete employee details", description = "Removes an employee's record from the system based on their unique ID. This operation is irreversible.")
    @APIResponses({
            @APIResponse(description = "OK", responseCode = "200"),
            @APIResponse(description = "Not found", responseCode = "404")
    })
    public Response deleteEmployeeInfo(@PathParam("id") Integer id){
        boolean success = employeeBean.deleteEmployee(id);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
