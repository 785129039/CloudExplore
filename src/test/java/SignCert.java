

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Date;

import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateIssuerName;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateValidity;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

public class SignCert {
	
	// ǩ����
	public static final String clientStore = "D:\\Credential\\client.jks";
	public static final String clientCertAlias = "client";
	
	// ��ǩ����
	public static final String serverCert = "D:\\Credential\\server.cer";
	
	// �µ���Կ��
	public static final String newServerStore = "D:\\Credential\\newserver.jks";
	public static final String newServerCertAlias = "signedserver";
	
	// ����
	public static final String password = "changeit";
	
	public static void main(String[] args) throws Exception{
		//ǩ������Ϣ
		FileInputStream fis = new FileInputStream(clientStore);
		KeyStore ks = KeyStore.getInstance("JKS");
		ks.load(fis, password.toCharArray());
		Certificate c1 = ks.getCertificate(clientCertAlias);
		PrivateKey caprk = (PrivateKey) ks.getKey(clientCertAlias, password.toCharArray());//֤��˽Կ
		fis.close();
		byte[] encode1 = c1.getEncoded();
		X509CertImpl cimpl = new X509CertImpl(encode1);
		X509CertInfo cinfol = (X509CertInfo) cimpl.get(X509CertImpl.NAME+"."+X509CertImpl.INFO);
		X500Name issuer = (X500Name) cinfol.get(X509CertInfo.SUBJECT+"."+CertificateIssuerName.DN_NAME);
		
		//��ǩ����
		FileInputStream fis2 = new FileInputStream(serverCert);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		Certificate c2 = cf.generateCertificate(fis2);
		fis2.close();
		byte[] encode2 = c2.getEncoded();
		X509CertImpl cimp2 = new X509CertImpl(encode2);
		X509CertInfo cinfo2 = (X509CertInfo) cimp2.get(X509CertImpl.NAME+"."+X509CertImpl.INFO);
		Date begindate = new Date();//������Ч��
		Date enddate = new Date(begindate.getTime()+3000*24*60*60*1000L);
		CertificateValidity cv = new CertificateValidity(begindate,enddate);
		cinfo2.set(X509CertInfo.VALIDITY, cv);
		int sn = (int) (begindate.getTime()/1000);//����֤������к�
		CertificateSerialNumber csn = new CertificateSerialNumber(sn);
		cinfo2.set(X509CertInfo.SERIAL_NUMBER, csn);
		cinfo2.set(X509CertInfo.ISSUER+"."+CertificateIssuerName.DN_NAME, issuer);//����֤���ǩ������˭
		AlgorithmId algorithm = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);//����֤����㷨
		cinfo2.set(CertificateAlgorithmId.NAME+"."+CertificateAlgorithmId.ALGORITHM, algorithm);
		X509CertImpl newcert = new X509CertImpl(cinfo2);//����һ��֤�鲢ʹ��ǩ���ߵ�˽Կ�Դ�ǩ���߽���ǩ��
		newcert.sign(caprk, "MD5WithRSA");
		System.out.println(newcert);
		ks.setCertificateEntry(newServerCertAlias, newcert);//������Ŀ���뵽��Կ����
		FileOutputStream fos = new FileOutputStream(newServerStore);//�ٰ���Կ�������һ���µ���Կ����
		ks.store(fos, password.toCharArray());// ������
		fos.close();
	}
}
