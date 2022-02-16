package com.coomeva.credisolidario.utilities;

public class Constants {

	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 18000;// 30*60; //tiempo de expiracion del token: 30
	// minutos
	public static final String SIGNING_KEY = "c00m3v42022*";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String NAME_APP_PROFILE = "SimuladorCredisolidario";
	public static final String DIRECTORY_APP_PROFILE = "2";
	public static final String VALIDATE_APP_PROFILE = "1";
	public static final Long NIVEL_CIUDAD = (long) 4;
	public static final Character ESTADO_ACTIVO = '1';
	public static final Character ESTADO_INACTIVO = '0';

	/* Mensajes de error Generales */
	public static final String ERROR_SAVE = "Ocurrio un error al guardar por favor intente mas tarde.";
	public static final String ERROR_GET_PARAMETER = "Ocurrio un error al obtener los parametros por favor intente mas tarde.";
	public static final String ERROR_GET_PROPERTY = "Ocurrio un error al obtener la propiedad.";
	public static final String ERROR_UPDATE_SOLICITUD = "Ocurrio un error al actualizar el estado de la solicitud.";
	public static final String ERROR_GET = "Ocurrio un error al consultar";

	/* id Tipo Parametro Estados de solicitud */
	public static final Long STATE_SOLICITUD = (long) 161;
	

// Mensajes de validacion familiar asociado
	public static final String ASOCIADO_EXISTE = "EXISTE";
	public static final String ASOCIADO_NO_EXISTE = "El asociado no existe, por favor validar";
	public static final String ASOCIADO_EXISTE_CONDICION_INVALIDA = "El asociado se encuentra en validación, por favor comuníquese con nuestros asesores";

}
