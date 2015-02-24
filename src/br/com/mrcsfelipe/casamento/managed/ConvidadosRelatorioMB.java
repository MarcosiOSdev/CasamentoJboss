package br.com.mrcsfelipe.casamento.managed;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import br.com.mrcsfelipe.casamento.business.ConvidadosBusiness;
import br.com.mrcsfelipe.casamento.business.UsuarioBusiness;
import br.com.mrcsfelipe.casamento.model.Convidados;
import br.com.mrcsfelipe.casamento.model.Usuario;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@ManagedBean(name="convidadosRelatorioMB")
@RequestScoped
public class ConvidadosRelatorioMB implements Serializable {

	
	@ManagedProperty(value="#{usuarioSessionMB}")
	private UsuarioSessionMB usuarioSessionMB;
	
	@Inject
	private ConvidadosBusiness convidadosBusiness;
	
	@Inject
	private UsuarioBusiness usuarioBusiness;
	
	private List<Convidados> lstConvidados;
	private Usuario usuario;
	
	@PostConstruct
	public void init(){
		this.usuario = this.usuarioSessionMB.getUsuario();
		lstConvidados = new ArrayList<Convidados>();
	}
	
	public ConvidadosRelatorioMB() {
		// TODO Auto-generated constructor stub
	}
	
	
	private static Font fonteCabecalho = new Font(Font.COURIER, 22, Font.BOLD);
	private static Font fonteRodape = new Font(Font.BOLD, 8, Font.ITALIC, new Color(0, 0, 0));
	private static Font fontePadrao = new Font(Font.TIMES_ROMAN, 12);
	private static Font fonteVermelha = new Font(Font.TIMES_ROMAN,	12, Font.NORMAL, Color.RED);
	private static Font negritoPequena = new Font(Font.HELVETICA, 10, Font.BOLD);
	private static Font negritoNormal = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
	
	
	
	
	public Paragraph pularLinha(){
		Paragraph pularLinha = new Paragraph(" ");
		return pularLinha;
	}
	
	
	public void createPDF() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline=filename=file.pdf");
		try {

//			List<String> listString = convidadosBusiness.buscarTodosConvidadosDoCasal(usuario.getLogin());
//			for (String string : listString) {
//				System.out.println(string);
//			}
			
			
			Document document = new Document();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			
			//RODAPÉ
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			HeaderFooter rodape = new HeaderFooter(
					new Phrase( "Data: " + sdf.format(new Date()) + "    " +
							    "Casamento de Marcos Felipe e Taiana" + "    "+
			                    "Página: "+document.getPageNumber(), fonteRodape),
			                    false);
			document.setFooter(rodape);
			
			//ABRINDO PDF 
			document.open();
			
			
			//CONFIGURANDO TAMANHO
			document.setPageSize(PageSize.A4);
						
			
			//TITULO == Convidados
			Paragraph titulo = new Paragraph("Convidados", fonteCabecalho);
			titulo.setAlignment(Element.ALIGN_CENTER);
			document.add(titulo);
			
			
			// PULANDO LINHA
			document.add(pularLinha());
			document.add(pularLinha());
			document.add(pularLinha());
			
			
			//TABELA DE 4 COLUNAS = Nº, Nome, Local
			PdfPTable table = new PdfPTable(
					new float[] {0.200f, 1.069f, 1.04f });
			
			PdfPCell c1 = new PdfPCell(new Phrase("Nº",negritoNormal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
 
			c1 = new PdfPCell(new Phrase("Nome",negritoNormal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
 
			c1 = new PdfPCell(new Phrase("Local", negritoNormal));
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(c1);
			
			
			table.setHeaderRows(1);
			
			int totalConvidados = getLstConvidados().size();
			
			
			
			for(int x = 0 ; x < totalConvidados; x++ ){
				int valorLinha = x + 1;
				table.addCell("" + valorLinha);
				table.addCell(lstConvidados.get(x).getNome());
				table.addCell(lstConvidados.get(x).getLocalidade().getNomeLocalidade());
			}
			
			
			document.add(table);
			
			
			
			//FECHANDO PDF
			document.close();

			// setting some response headers
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control",
					"must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			// setting the content type
			response.setContentType("application/pdf");
			// the contentlength
			response.setContentLength(baos.size());
			// write ByteArrayOutputStream to the ServletOutputStream
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();

		} catch (DocumentException e) {
		} catch (IOException e) {
		} catch (Exception ex) {
		}
		context.responseComplete();
	}
	
	/***************************************************************************
	 * Getter & Setter
	 **************************************************************************/
	public List<Convidados> getLstConvidados() {
		lstConvidados = convidadosBusiness.buscarTodosDoCasal(usuario.getLogin());
		lstConvidados.addAll(convidadosBusiness.buscarTodosDoCasal(usuarioBusiness.buscarCasalDoUsuario(usuario).getLogin()));
		Collections.sort(lstConvidados);
		return lstConvidados;
	}


	public UsuarioSessionMB getUsuarioSessionMB() {
		return usuarioSessionMB;
	}

	public void setUsuarioSessionMB(UsuarioSessionMB usuarioSessionMB) {
		this.usuarioSessionMB = usuarioSessionMB;
	}
	
	

}
