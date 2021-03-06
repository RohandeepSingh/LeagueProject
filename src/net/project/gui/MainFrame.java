package net.project.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import net.project.Constants;
import net.project.Converter;
import net.project.api.CallException;
import net.project.api.CallRiot;
import net.project.api.currentgame.CurrentGameInfo;
import net.project.api.stats.AggregatedStats;
import net.project.api.stats.RankedStats;
import net.project.api.summoner.Summoner;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener, MouseListener {

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
	private JTabbedPane tabbedPane;
	private Summoner summoner;
	private String region;
	private JLabel lblSummonerName_1;
	private JLabel lblTotalGames;
	private JLabel lblTotalWins;
	private JLabel lblTotalLosses;
	private JLabel totalGames;
	private JLabel totalWins;
	private JLabel totalLosses;
	private CallRiot call;
	private Converter converter;

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
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

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
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					searchSummoner();
			}
		});
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
		lblId.addMouseListener(this);

		lblName = new JLabel("");
		lblName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblName.addMouseListener(this);

		lblProfileIconId = new JLabel("");
		lblProfileIconId.setFont(new Font("Arial", Font.PLAIN, 12));
		lblProfileIconId.addMouseListener(this);

		lblRevisionDate = new JLabel("");
		lblRevisionDate.addMouseListener(this);
		lblRevisionDate.setLabelFor(lblRevisionDate);
		lblRevisionDate.setFont(new Font("Arial", Font.PLAIN, 12));

		lblLevel = new JLabel("");
		lblLevel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLevel.addMouseListener(this);

		lblProfileIcon = new JLabel("");
		lblProfileIcon.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_summonerSearch = new GroupLayout(summonerSearch);
		gl_summonerSearch.setHorizontalGroup(
				gl_summonerSearch.createParallelGroup(Alignment.LEADING).addGroup(gl_summonerSearch
						.createSequentialGroup().addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
								.addGroup(
										gl_summonerSearch.createSequentialGroup().addGap(72).addGroup(gl_summonerSearch
												.createParallelGroup(Alignment.LEADING).addGroup(gl_summonerSearch
														.createSequentialGroup().addComponent(lblSummonerName)
														.addPreferredGap(ComponentPlacement.RELATED).addComponent(
																textField, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(4).addComponent(regionBox, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(4).addComponent(btnSearch))
												.addComponent(separator, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_summonerSearch.createSequentialGroup().addGap(42)
										.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
												.addComponent(lblError).addGroup(gl_summonerSearch
														.createSequentialGroup()
														.addGroup(gl_summonerSearch
																.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_summonerSearch.createSequentialGroup()
																		.addComponent(lblSummonerProfileIcon)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(lblProfileIconId,
																				GroupLayout.PREFERRED_SIZE, 42,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_summonerSearch.createSequentialGroup()
																		.addComponent(label)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(lblId))
																.addGroup(gl_summonerSearch.createSequentialGroup()
																		.addComponent(lblSummonerLevel)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(lblLevel,
																				GroupLayout.PREFERRED_SIZE, 42,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_summonerSearch.createSequentialGroup()
																		.addComponent(lblSummonerRevisionDate)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(lblRevisionDate,
																				GroupLayout.PREFERRED_SIZE, 115,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_summonerSearch.createSequentialGroup()
																		.addComponent(lblNewLabel)
																		.addPreferredGap(ComponentPlacement.RELATED)
																		.addComponent(lblName, GroupLayout.DEFAULT_SIZE,
																				233, Short.MAX_VALUE)
																		.addPreferredGap(ComponentPlacement.RELATED)))
														.addGroup(gl_summonerSearch
																.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_summonerSearch.createSequentialGroup()
																		.addPreferredGap(ComponentPlacement.RELATED,
																				231, Short.MAX_VALUE)
																		.addComponent(layeredPane,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_summonerSearch.createSequentialGroup()
																		.addGap(65).addComponent(lblProfileIcon)))))))
						.addGap(36)));
		gl_summonerSearch.setVerticalGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_summonerSearch.createSequentialGroup()
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addGap(5)
						.addGroup(gl_summonerSearch
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_summonerSearch.createSequentialGroup().addGap(2).addComponent(textField,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_summonerSearch.createSequentialGroup().addGap(2).addComponent(regionBox,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSearch).addComponent(lblSummonerName, GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE))
						.addGap(46)
						.addGroup(gl_summonerSearch.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_summonerSearch.createSequentialGroup()
										.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(11).addComponent(lblProfileIcon)
										.addPreferredGap(ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
										.addComponent(lblError))
								.addGroup(gl_summonerSearch.createSequentialGroup()
										.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
												.addComponent(label).addComponent(lblId))
										.addGap(5)
										.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblNewLabel).addComponent(lblName,
														GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
										.addGap(5)
										.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblSummonerProfileIcon).addComponent(lblProfileIconId,
														GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
										.addGap(5)
										.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblSummonerRevisionDate).addComponent(lblRevisionDate,
														GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
										.addGap(5)
										.addGroup(gl_summonerSearch.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblSummonerLevel).addComponent(lblLevel,
														GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		summonerSearch.setLayout(gl_summonerSearch);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, 684, GroupLayout.PREFERRED_SIZE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE));
		
		Panel rankedStats = new Panel();
		tabbedPane.addTab("Ranked Stats", null, rankedStats, null);
		tabbedPane.setEnabledAt(2, false);
		SpringLayout sl_rankedStats = new SpringLayout();
		rankedStats.setLayout(sl_rankedStats);
		
		lblSummonerName_1 = new JLabel("Mintzy");
		sl_rankedStats.putConstraint(SpringLayout.NORTH, lblSummonerName_1, 10, SpringLayout.NORTH, rankedStats);
		sl_rankedStats.putConstraint(SpringLayout.WEST, lblSummonerName_1, 10, SpringLayout.WEST, rankedStats);
		lblSummonerName_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
		rankedStats.add(lblSummonerName_1);
		
		lblTotalGames = new JLabel("Total Games:");
		sl_rankedStats.putConstraint(SpringLayout.NORTH, lblTotalGames, 74, SpringLayout.NORTH, rankedStats);
		sl_rankedStats.putConstraint(SpringLayout.WEST, lblTotalGames, 97, SpringLayout.WEST, rankedStats);
		lblTotalGames.setFont(new Font("Arial", Font.BOLD, 15));
		rankedStats.add(lblTotalGames);
		
		lblTotalWins = new JLabel("Total Wins:");
		sl_rankedStats.putConstraint(SpringLayout.NORTH, lblTotalWins, 6, SpringLayout.SOUTH, lblTotalGames);
		sl_rankedStats.putConstraint(SpringLayout.WEST, lblTotalWins, 0, SpringLayout.WEST, lblTotalGames);
		lblTotalWins.setFont(new Font("Arial", Font.BOLD, 15));
		rankedStats.add(lblTotalWins);
		
		lblTotalLosses = new JLabel("Total Losses:");
		sl_rankedStats.putConstraint(SpringLayout.NORTH, lblTotalLosses, 6, SpringLayout.SOUTH, lblTotalWins);
		sl_rankedStats.putConstraint(SpringLayout.WEST, lblTotalLosses, 0, SpringLayout.WEST, lblTotalGames);
		lblTotalLosses.setFont(new Font("Arial", Font.BOLD, 15));
		rankedStats.add(lblTotalLosses);
		
		totalGames = new JLabel("");
		sl_rankedStats.putConstraint(SpringLayout.WEST, totalGames, 6, SpringLayout.EAST, lblTotalGames);
		sl_rankedStats.putConstraint(SpringLayout.SOUTH, totalGames, 0, SpringLayout.SOUTH, lblTotalGames);
		totalGames.setFont(new Font("Arial", Font.BOLD, 15));
		rankedStats.add(totalGames);
		
		totalWins = new JLabel("");
		sl_rankedStats.putConstraint(SpringLayout.WEST, totalWins, 6, SpringLayout.EAST, lblTotalWins);
		sl_rankedStats.putConstraint(SpringLayout.SOUTH, totalWins, 0, SpringLayout.SOUTH, lblTotalWins);
		totalWins.setFont(new Font("Arial", Font.BOLD, 15));
		rankedStats.add(totalWins);
		
		totalLosses = new JLabel("");
		sl_rankedStats.putConstraint(SpringLayout.NORTH, totalLosses, 0, SpringLayout.NORTH, lblTotalLosses);
		sl_rankedStats.putConstraint(SpringLayout.WEST, totalLosses, 6, SpringLayout.EAST, lblTotalLosses);
		totalLosses.setFont(new Font("Arial", Font.BOLD, 15));
		rankedStats.add(totalLosses);

		JTabbedPane currentGame = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Current Game", null, currentGame, null);
		tabbedPane.setEnabledAt(3, false);
		contentPane.setLayout(gl_contentPane);
		call = new CallRiot();
		converter = new Converter();
	}

	private void searchSummoner() {
		if (textField.getText().isEmpty()) {
			lblError.setText("You need to enter a summoner name!");
		} else {
			try {
				lblError.setText(null);
				region = regionBox.getSelectedItem().toString();
				summoner = converter.getSummoner(textField.getText().toLowerCase(), region);
				if (summoner != null) {
					lblId.setText(Long.toString(summoner.getId()));
					lblName.setText(summoner.getName());
					lblProfileIconId.setText(Long.toString(summoner.getProfileIconId()));
					lblRevisionDate.setText(
							new SimpleDateFormat("MM/dd/yyyy HH:mm").format(new Date(summoner.getRevisionDate())));
					lblLevel.setText(Long.toString(summoner.getSummonerLevel()));
					ImageIcon icon = converter.obtainProfileIcon(summoner.getProfileIconId());
					lblProfileIcon.setIcon(icon);
					loadRankedStats(summoner);
					tabbedPane.setEnabledAt(2, true);
					try {
						call.setRegion(region);
						call.modifyURL(Constants.GET_CURRENT_GAME, Long.toString(summoner.getId()));
						CurrentGameInfo currentGame = converter.getCurrentGameInfo(call.now());
						if (!currentGame.equals(null)) {
							tabbedPane.setEnabledAt(3, true);
						}
					} catch (CallException e) {
						System.out.println("Summoner not in game..");
						tabbedPane.setEnabledAt(3, false);
					}
				}
			} catch (CallException e1) {
				lblError.setText(e1.getMessage());
				clearText();
				tabbedPane.setEnabledAt(2, false);
				tabbedPane.setEnabledAt(3, false);
			}
		}
	}
	
	private void loadRankedStats(Summoner summoner) {
		try {
			call.setRegion(region);
			call.modifyURL(Constants.GET_RANKED_STATS, Long.toString(summoner.getId()));
			RankedStats stats = converter.getRankedStats(call.now());
			for (int i = 0; i < stats.getChampions().size(); i++) {
				if (stats.getChampions().get(i).getId() == 0) {
					AggregatedStats allStats = stats.getChampions().get(i).getStats();
					totalGames.setText(Integer.toString(allStats.getTotalSessionsPlayed()));
					totalWins.setText((Integer.toString(allStats.getTotalSessionsWon())));
					totalLosses.setText((Integer.toString(allStats.getTotalSessionsLost())));
				}
			}
		} catch (CallException e) {
			System.out.println(e.getMessage());
		}
		lblSummonerName_1.setText(summoner.getName());
	}

	private void clearText() {
		lblId.setText(null);
		lblName.setText(null);
		lblProfileIconId.setText(null);
		lblRevisionDate.setText(null);
		lblLevel.setText(null);
		lblProfileIcon.setIcon(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Search")) {
			searchSummoner();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getComponent().equals(lblRevisionDate) || e.getComponent().equals(lblId)
				|| e.getComponent().equals(lblName) || e.getComponent().equals(lblProfileIconId)
				|| e.getComponent().equals(lblLevel)) {
			JLabel clickedLbl = (JLabel) e.getComponent();
			clickedLbl = (JLabel) e.getComponent();
			String text = new String(clickedLbl.getText());
			if (!text.isEmpty()) {
				StringSelection selection = new StringSelection(text);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selection, selection);
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
