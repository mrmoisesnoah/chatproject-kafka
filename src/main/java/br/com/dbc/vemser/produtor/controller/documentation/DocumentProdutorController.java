package br.com.dbc.vemser.produtor.controller.documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface DocumentProdutorController {
    @Operation(summary = "Send message", description = "Produce and insert a message in the Topic to the specific Broker of a Cluster")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Send successfully."),
            @ApiResponse(responseCode = "400", description = "Message not sent."),
            @ApiResponse(responseCode = "403", description = "You are not authorized to perform this operation.")
    })
    public void enviarMensagem(String mensagem);


}
