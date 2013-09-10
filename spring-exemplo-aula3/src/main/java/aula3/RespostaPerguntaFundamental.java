package aula3;

public class RespostaPerguntaFundamental {

	private String pergunta;
	private Integer resposta;
	
	public RespostaPerguntaFundamental() {
		this.pergunta = "";
		this.resposta = -1;
	}
	
	public RespostaPerguntaFundamental(final String pergunta, final Integer resposta) {
		this.pergunta = pergunta;
		this.resposta = resposta;
	}
	
	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(final String pergunta) {
		this.pergunta = pergunta;
	}

	public Integer getResposta() {
		return resposta;
	}

	public void setResposta(final Integer resposta) {
		this.resposta = resposta;
	}

	@Override
	public String toString() {
		return pergunta + "\n Resposta: " + resposta;
	}
}
