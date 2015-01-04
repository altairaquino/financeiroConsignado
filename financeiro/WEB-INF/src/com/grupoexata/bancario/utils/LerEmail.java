package com.grupoexata.bancario.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

import com.jscape.inet.email.EmailMessage;
import com.jscape.inet.mime.Attachment;
import com.jscape.inet.mime.MimeException;
import com.jscape.inet.pop.PopException;
import com.jscape.inet.popssl.PopSsl;

public class LerEmail {

	private static File messagesDir = new File("msg");
	private static File attachmentsDir = new File("attach");
	static BufferedReader reader = new BufferedReader(new InputStreamReader(
			System.in));

	// get messages from Pop mailbox
	public void getMessages(String hostname, String username, String password)
			throws PopException, MimeException, IOException {
		// ensure that messages directory is valid
		if (!messagesDir.exists()) {
			messagesDir.mkdir();
		}

		// ensure that attachments directory is valid
		if (!attachmentsDir.exists()) {
			attachmentsDir.mkdir();
		}

		// connect and login
//		Pop pop = new Pop(hostname, username, password);
//		pop.setDelete(false);
//		pop.connect();
		PopSsl ssl = new PopSsl(hostname,995,username,password);
		ssl.setDebug(true);
		ssl.setDelete(false);
		ssl.connect();

		System.out.println("Retrieving messsages...");

		// get messages
		Enumeration<EmailMessage> messages = ssl.getMessages();
		if (!messages.hasMoreElements()) {
			System.out.println("No messages found");
		}

		// write messages to disk
		int msgnum = 0;
		while (messages.hasMoreElements()) {
			++msgnum;
			EmailMessage msg = (EmailMessage) messages.nextElement();
			File f = new File(messagesDir, "msg" + msgnum + ".txt");
			FileOutputStream fout = new FileOutputStream(f);
//			fout.write(msg.getMessage());
			fout.write(msg.getBodyData());
			fout.close();
			System.out.println("Retrieved message: " + f.getAbsoluteFile());

			// write attachments to disk
			int attachnum = 0;
			Enumeration<Attachment> attachments = msg.getAttachments();
			while (attachments.hasMoreElements()) {
				++attachnum;
				Attachment attach = (Attachment) attachments.nextElement();
				String attachmentFileName = attach.getFilename();
				if (attachmentFileName == null) {
					attachmentFileName = "attachment" + msgnum + "-"
							+ attachnum;
				}

				String fileName = attach.getFilename();
				if (fileName == null) {
					fileName = System.currentTimeMillis() + ".txt";
				}
				File attFile = new File(attachmentsDir, fileName);
				FileOutputStream attOut = new FileOutputStream(attFile);
				try {
					attOut.write(attach.getFileData());
					attOut.close();
					System.out.println("Retrieved attachment: "
							+ attFile.getAbsoluteFile());
				} catch (Exception e) {
					throw new PopException("unable to decode file attachment");
				}
			}
		}
		ssl.disconnect();
	}

	public static void main(String[] args) {
		String hostname;
		String username;
		String password;

		try {
//			System.out.print("Enter Pop3 hostname (e.g. mail.domain.com): ");
//			hostname = reader.readLine();
			hostname = "pop.gmail.com";
			System.out.print("Enter Pop3 Username: ");
			username = reader.readLine();
			System.out.print("Enter Pop3 Password: ");
			password = reader.readLine();
			LerEmail example = new LerEmail();
			example.getMessages(hostname, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
