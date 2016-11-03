package net.project.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import net.project.Converter;
import net.project.api.CallException;
import net.project.api.summoner.Summoner;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblError;
	private JComboBox<String> regionBox;
	private JButton btnSearch;
	private JLabel lblProfileIconId;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblRevisionDate;
	private JLabel lblLevel;
	private JLabel lblProfileIcon;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MainFrame() {
		setResizable(false);
		setTitle("League of Legends API Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		Panel main = new Panel();
		tabbedPane.addTab("Main", null, main, null);

		JTextPane txtpnNews = new JTextPane();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		txtpnNews.setParagraphAttributes(center, false);
		txtpnNews.setEditable(false);
		txtpnNews.setFont(new Font("Arial", Font.PLAIN, 13));
		txtpnNews.setBackground(Color.BLACK);
		txtpnNews.setForeground(Color.WHITE);
		txtpnNews
				.setText("Welcome to my League of Legends API Project!\r\n\r\nNews & Updates can be put onto this tab");
		main.add(txtpnNews);

		Panel summonerSearch = new Panel();
		tabbedPane.addTab("Summoner Search", null, summonerSearch, null);

		JLabel lblSummonerName = new JLabel("Summoner Name:");
		lblSummonerName.setFont(new Font("Arial", Font.PLAIN, 15));

		textField = new JTextField();
		textField.setColumns(10);
		btnSearch = new JButton("Search");

		btnSearch.addActionListener(this);

		regionBox = new JComboBox();
		regionBox.setFont(new Font("Arial", Font.PLAIN, 12));
		regionBox.setModel(new DefaultComboBoxModel(new String[] { "NA", "KR", "EUW" }));
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Arial", Font.BOLD, 20));

		JLabel label = new JLabel("Summoner ID:");
		label.setFont(new Font("Arial", Font.BOLD, 12));

		JSeparator separator = new JSeparator();

		JLabel lblNewLabel = new JLabel("Summoner Name:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblSummonerProfileIcon = new JLabel("Summoner Profile Icon ID:");
		lblSummonerProfileIcon.setFont(new Font("Arial", Font.BOLD, 12));

		JLayeredPane layeredPane = new JLayeredPane();

		JLabel lblSummonerRevisionDate = new JLabel("Summoner Revision Date:");
		lblSummonerRevisionDate.setFont(new Font("Arial", Font.BOLD, 12));

		JLabel lblSummonerLevel = new JLabel("Summoner Level:");
		lblSummonerLevel.setFont(new Font("Arial", Font.BOLD, 12));

		lblId = new JLabel("");
		lblId.setFont(new Font("Arial", Font.PLAIN, 12));

		lblName = new JLabel("");
		lblName.setFont(new Font("Arial", Font.PLAIN, 12));

		lblProfileIconId = new JLabel("");
		lblProfileIconId.setFont(new Font("Arial", Font.PLAIN, 12));

		lblRevisionDate = new JLabel("");
		lblRevisionDate.setLabelFor(lblRevisionDate);
		lblRevisionDate.setFont(new Font("Arial", Font.PLAIN, 12));

		lblLevel = new JLabel("");
		lblLevel.setFont(new Font("Arial", Font.PLAIN, 12));

		lblProfileIcon = new JLabel("");
		lblProfileIcon.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_summonerSearch = new GroupLayout(summonerSearch);
		gl_summonerSearch.setHorizontalGroup(
			gl_summonerSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_summonerSearch.createSequentialGroup()
					.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_summonerSearch.createSequentialGroup()
							.addGap(72)
							.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_summonerSearch.createSequentialGroup()
									.addComponent(lblSummonerName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(regionBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(btnSearch))
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_summonerSearch.createSequentialGroup()
							.addGap(42)
							.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
								.addComponent(lblError)
								.addGroup(gl_summonerSearch.createSequentialGroup()
									.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_summonerSearch.createSequentialGroup()
											.addComponent(lblSummonerProfileIcon)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblProfileIconId, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_summonerSearch.createSequentialGroup()
											.addComponent(label)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblId))
										.addGroup(gl_summonerSearch.createSequentialGroup()
											.addComponent(lblSummonerLevel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_summonerSearch.createSequentialGroup()
											.addComponent(lblSummonerRevisionDate)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblRevisionDate, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_summonerSearch.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblName, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_summonerSearch.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
											.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_summonerSearch.createSequentialGroup()
											.addGap(65)
											.addComponent(lblProfileIcon)))))))
					.addGap(36))
		);
		gl_summonerSearch.setVerticalGroup(
			gl_summonerSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_summonerSearch.createSequentialGroup()
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_summonerSearch.createSequentialGroup()
							.addGap(2)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_summonerSearch.createSequentialGroup()
							.addGap(2)
							.addComponent(regionBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSearch)
						.addComponent(lblSummonerName, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_summonerSearch.createSequentialGroup()
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblProfileIcon)
							.addPreferredGap(ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
							.addComponent(lblError))
						.addGroup(gl_summonerSearch.createSequentialGroup()
							.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
								.addComponent(label)
								.addComponent(lblId))
							.addGap(5)
							.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSummonerProfileIcon)
								.addComponent(lblProfileIconId, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSummonerRevisionDate)
								.addComponent(lblRevisionDate, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
							.addGap(5)
							.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSummonerLevel)
								.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		summonerSearch.setLayout(gl_summonerSearch);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Search")) {
			if (textField.getText().isEmpty()) {
				lblError.setText("You need to enter a summoner name!");
				;
			} else {
				try {
					Converter converter = new Converter();
					Summoner summoner = converter.toSummoner(textField.getText().toLowerCase(),
							regionBox.getSelectedItem().toString());
					if (summoner != null) {
						lblId.setText(Long.toString(summoner.getId()));
						lblName.setText(summoner.getName());
						lblProfileIconId.setText(Long.toString(summoner.getProfileIconId()));
						lblRevisionDate.setText(
								new SimpleDateFormat("MM/dd/yyyy HH:mm").format(new Date(summoner.getRevisionDate())));
						lblLevel.setText(Long.toString(summoner.getSummonerLevel()));
						ImageIcon icon = converter.obtainProfileIcon(Long.toString(summoner.getProfileIconId()));
						lblProfileIcon.setIcon(icon);
					}
				} catch (CallException e1) {
					lblError.setText(e1.getMessage());
					System.out.println(e1.getMessage());
				}
			}
		}

	}

}
