package miragefairy2018.colormaker.core;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import javax.swing.JPanel;

import mirrg.beryllium.lang.LambdaUtil;

public class PanelColorSlider extends JPanel
{

	private PanelSliderField sliderG;
	private PanelSliderField sliderB;
	private PanelSliderField sliderR;
	private ParsingTextField<Integer> textField;

	private boolean isInProcessing = false;

	public PanelColorSlider()
	{

		setLayout(LambdaUtil.process(new GridBagLayout(), l -> {
			l.columnWidths = new int[] {
				0, 0
			};
			l.rowHeights = new int[] {
				0, 0, 0, 0
			};
			l.columnWeights = new double[] {
				1.0, Double.MIN_VALUE
			};
			l.rowWeights = new double[] {
				0.0, 0.0, 0.0, 0.0
			};
		}));

		add(LambdaUtil.process(sliderR = new PanelSliderField(), c -> {
			c.listeners.add(value -> {
				if (isInProcessing) return;
				setValue(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()), c);
			});
		}), LambdaUtil.process(new GridBagConstraints(), c -> {
			c.insets = new Insets(0, 0, 5, 0);
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
		}));

		add(LambdaUtil.process(sliderG = new PanelSliderField(), c -> {
			c.listeners.add(value -> {
				if (isInProcessing) return;
				setValue(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()), c);
			});
		}), LambdaUtil.process(new GridBagConstraints(), c -> {
			c.insets = new Insets(0, 0, 5, 0);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 1;
		}));

		add(LambdaUtil.process(sliderB = new PanelSliderField(), c -> {
			c.listeners.add(value -> {
				if (isInProcessing) return;
				setValue(new Color(sliderR.getValue(), sliderG.getValue(), sliderB.getValue()), c);
			});
		}), LambdaUtil.process(new GridBagConstraints(), c -> {
			c.insets = new Insets(0, 0, 5, 0);
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 2;
		}));

		add(LambdaUtil.process(textField = new ParsingTextField<>(), c -> {
			Pattern pattern = Pattern.compile("[0-9a-fA-F]{6}");
			c.parser = s -> {
				if (pattern.matcher(s.trim()).matches()) {
					return Optional.of(Integer.parseInt(s.trim(), 16));
				} else {
					return Optional.empty();
				}
			};
			c.builder = v -> String.format("%06X", v & 0xffffff);
			c.setColumns(10);
			c.listener.add(i -> {
				if (isInProcessing) return;
				setValue(new Color(c.getValue()), c);
			});
		}), LambdaUtil.process(new GridBagConstraints(), c -> {
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 3;
		}));

		//

		setValue(Color.white);

	}

	//

	private Color value;

	public void setValue(Color value)
	{
		setValue(value, null);
	}

	public Color getValue()
	{
		return value;
	}

	//

	public ArrayList<Consumer<Color>> listeners = new ArrayList<>();

	private void setValue(Color value, Object source)
	{
		isInProcessing = true;

		this.value = value;
		if (source != textField) textField.setValue(value.getRGB());
		if (source != sliderR) sliderR.setValue(value.getRed());
		if (source != sliderG) sliderG.setValue(value.getGreen());
		if (source != sliderB) sliderB.setValue(value.getBlue());
		listeners.forEach(l -> {
			try {
				l.accept(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		isInProcessing = false;
	}

}
