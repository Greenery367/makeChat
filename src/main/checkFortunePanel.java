package main;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;

public class checkFortunePanel extends JPanel {

	private JPanel backgroundPanel;
	
	private JList<String> userList;
	
	private JButton fortuneBtn;
	private JButton fortuneReBtn;
	
	private JLabel title;
	private JLabel title1;
	private JLabel luckScript;
	private JLabel image;
	
	private Vector<String> clientIdVector=new Vector<>();
	
	private CallBackClientbtwService callBackService;
	
	public checkFortunePanel(CallBackClientbtwService callBackService) {
		this.callBackService=callBackService;
		initObject();
		initSetting();
		initListener();
	}
	
	private void initObject() {
		backgroundPanel=new JPanel();
		userList=new JList<>();
		
		title=new JLabel("Nate on 운세보기에 오신 것을 환영합니다.");
		title1=new JLabel("오늘의 운세를 확인해보세요.");
		luckScript=new JLabel();
		image=new JLabel(new ImageIcon("images/fortune.png"));
		
		fortuneBtn=new JButton("운세 보기");
		fortuneReBtn=new JButton("운세 다시 보기");
	}

	private void initSetting() {
		setSize(getWidth(), getHeight());
		setLayout(null);
		setVisible(true);
		
		image.setBounds(85,80,200,200);
		title.setBounds(75,290,250,30);
		title1.setBounds(110,310,250,30);
		fortuneBtn.setBounds(125,370,120,40);
		
		add(image);
		add(title);
		add(title1);
		add(luckScript);
		add(fortuneBtn);
	}
	
	public void checkFortune() {
		Random random=new Random();
		int luck=random.nextInt(7);
		String luckText;
		if(luck==0) {
			luckText=" 주변의 사람들에게 친절을 베풀면 큰 행운이 되어 돌아옵니다.";
		} else if(luck==1) {
			luckText="모르는 것이 생긴다면 주저하지 말고 물어봅시다. 좋은 깨달음을 얻을지도 모릅니다.";
		} else if(luck==2) {
			luckText="서두르기보다는 천천히 일을 해나가는 게 좋습니다. 돌아가는 길이 지름길입니다.";
		} else if(luck==3) {
			luckText="과욕은 금물입니다. 휴식을 취하면서 내일을 준비해봅시다.";
		} else if(luck==4) {
			luckText="소소한 취미를 즐기며, 여유를 되찾는 것이 좋습니다.";
		} else if(luck==5) {
			luckText="타인의 말을 수용하고, 발전의 거름으로 삼도록 합시다.";
		}else if(luck==6) {
			luckText="주변 사람의 도움을 받아 승승장구하게 됩니다. 타인의 말을 귀담아 들읍시다.";
		}else {
			luckText="뜻하지 않던 기회가 옵니다. 생각만 하던 일들을 시작해보는 것도 좋을 것 같습니다.";
		}
		String text=luckText;
		JOptionPane.showMessageDialog(null,text,"오늘의 운세",JOptionPane.PLAIN_MESSAGE,null);
	}
	
	private void initListener() {
		fortuneBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				checkFortune();
			}
		});
	}
}
