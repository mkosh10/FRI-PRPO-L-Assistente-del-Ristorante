package si.uni.lj.prpo.projekt04.api.v1.sources;


import si.uni.lj.prpo.projekt04.DTOs.EmployeeDTO;
import si.uni.lj.prpo.projekt04.beans.EmployeeBean;
import si.uni.lj.prpo.projekt04.management.EmployeeBeanManagement;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeSource {
    @Inject
    private EmployeeBean employeeBean;

    @Inject
    private EmployeeBeanManagement employeeBeanManagement;

    @GET
    public Response getAllEmployees(){
        return Response
                .ok(employeeBean.getAllEmployees())
                .header("X-Total-Count", 10)
                .build();
    }


    @GET
    @Path("{id}")
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
    public Response addNewEmployee(EmployeeDTO employeeDTO){
        boolean success = employeeBeanManagement.addNewEmployee(employeeDTO);
        if(success){
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @PUT
    @Path("{id}")
    public Response updateEmployeeInfo(@PathParam("id") Integer id,  EmployeeDTO employeeDTO){
        boolean success = employeeBeanManagement.updateEmployeeInfo(id, employeeDTO);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployeeInfo(@PathParam("id") Integer id){
        boolean success = employeeBean.deleteEmployee(id);
        if(success){
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
